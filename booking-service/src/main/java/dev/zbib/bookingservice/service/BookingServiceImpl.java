package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.ProviderClient;
import dev.zbib.bookingservice.client.UserClient;
import dev.zbib.bookingservice.dto.*;
import dev.zbib.bookingservice.exception.*;
import dev.zbib.bookingservice.mapper.BookingMapper;
import dev.zbib.bookingservice.model.Booking;
import dev.zbib.bookingservice.model.BookingStatus;
import dev.zbib.bookingservice.repository.BookingRepository;
import dev.zbib.bookingservice.repository.BookingSpecifications;
import dev.zbib.shared.dto.PageRequest;
import dev.zbib.shared.exception.ServiceException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final ProviderClient providerClient;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    @CircuitBreaker(name = "createBooking", fallbackMethod = "createBookingFallback")
    public BookingResponse createBooking(CreateBookingRequest request) {
        try {
            UserDto user = getUserOrThrow(request.getUserId());
            ProviderDto provider = getProviderOrThrow(request.getProviderId());

            validateBookingCreation(user, provider, request);

            Booking booking = buildBooking(request);
            Booking savedBooking = bookingRepository.save(booking);
            
            log.info("Created new booking with ID: {} for user: {} and provider: {}", 
                    savedBooking.getId(), request.getUserId(), request.getProviderId());
            return bookingMapper.toResponse(savedBooking);
        } catch (Exception e) {
            log.error("Error creating booking: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toResponse)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
    }

    @Override
    @Transactional
    public BookingResponse updateBooking(Long id, UpdateBookingRequest request) {
        try {
            Booking booking = getBookingOrThrow(id);
            
            if (booking.getStatus() != BookingStatus.PENDING) {
                throw new InvalidBookingStateException("Can only update pending bookings");
            }

            if (request.getScheduledStartTime() != null && request.getScheduledEndTime() != null) {
                validateBookingTime(request.getScheduledStartTime(), request.getScheduledEndTime());
                validateProviderAvailability(booking.getProviderId(), 
                        request.getScheduledStartTime(), request.getScheduledEndTime());
                
                booking.setScheduledStartTime(request.getScheduledStartTime());
                booking.setScheduledEndTime(request.getScheduledEndTime());
            }

            if (request.getServiceAddress() != null) {
                booking.setServiceAddress(request.getServiceAddress());
            }
            if (request.getDescription() != null) {
                booking.setDescription(request.getDescription());
            }

            Booking updatedBooking = bookingRepository.save(booking);
            log.info("Updated booking with ID: {}", id);
            return bookingMapper.toResponse(updatedBooking);
        } catch (Exception e) {
            log.error("Error updating booking: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public BookingResponse updateBookingStatus(Long id, BookingStatus newStatus) {
        try {
            Booking booking = getBookingOrThrow(id);
            validateStatusTransition(booking.getStatus(), newStatus);
            
            booking.setStatus(newStatus);
            handleStatusSpecificActions(booking, newStatus);
            
            Booking updatedBooking = bookingRepository.save(booking);
            log.info("Updated booking status to {} for booking ID: {}", newStatus, id);
            return bookingMapper.toResponse(updatedBooking);
        } catch (Exception e) {
            log.error("Error updating booking status: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public BookingResponse startService(Long id) {
        try {
            Booking booking = getBookingOrThrow(id);
            
            if (booking.getStatus() != BookingStatus.ACCEPTED) {
                throw new InvalidBookingStateException("Booking must be in ACCEPTED state to start service");
            }

            booking.setStatus(BookingStatus.IN_PROGRESS);
            booking.setActualStartTime(LocalDateTime.now());

            Booking updatedBooking = bookingRepository.save(booking);
            log.info("Started service for booking ID: {}", id);
            return bookingMapper.toResponse(updatedBooking);
        } catch (Exception e) {
            log.error("Error starting service: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public BookingResponse completeService(Long id, Double totalCost) {
        try {
            Booking booking = getBookingOrThrow(id);
            
            if (booking.getStatus() != BookingStatus.IN_PROGRESS) {
                throw new InvalidBookingStateException("Can only complete bookings that are in progress");
            }

            booking.setStatus(BookingStatus.COMPLETED);
            booking.setActualEndTime(LocalDateTime.now());
            booking.setTotalCost(totalCost);

            Booking updatedBooking = bookingRepository.save(booking);
            log.info("Completed service for booking ID: {} with total cost: {}", id, totalCost);
            return bookingMapper.toResponse(updatedBooking);
        } catch (Exception e) {
            log.error("Error completing service: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public BookingResponse addRatingAndFeedback(Long id, Double rating, String feedback) {
        try {
            Booking booking = getBookingOrThrow(id);
            
            if (booking.getStatus() != BookingStatus.COMPLETED) {
                throw new InvalidBookingStateException("Can only add rating and feedback to completed bookings");
            }

            if (booking.getRating() != null) {
                throw new BookingValidationException("Booking has already been rated");
            }

            booking.setRating(rating);
            booking.setFeedback(feedback);

            Booking updatedBooking = bookingRepository.save(booking);
            log.info("Added rating {} and feedback for booking ID: {}", rating, id);
            return bookingMapper.toResponse(updatedBooking);
        } catch (Exception e) {
            log.error("Error adding rating and feedback: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteBooking(Long id) {
        try {
            Booking booking = getBookingOrThrow(id);
            
            if (booking.getStatus() != BookingStatus.PENDING) {
                throw new InvalidBookingStateException("Can only delete pending bookings");
            }

            bookingRepository.delete(booking);
            log.info("Deleted booking with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting booking: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isProviderAvailable(Long providerId, LocalDateTime start, LocalDateTime end) {
        try {
            getProviderOrThrow(providerId);
            validateDateRange(start, end);
            
            List<BookingStatus> conflictingStatuses = Arrays.asList(
                BookingStatus.PENDING,
                BookingStatus.ACCEPTED,
                BookingStatus.IN_PROGRESS
            );
            
            return !bookingRepository.existsOverlappingBooking(providerId, conflictingStatuses, start, end);
        } catch (Exception e) {
            log.error("Error checking provider availability: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> findBookings(BookingFilterCriteria criteria, PageRequest pageRequest) {
        try {
            Specification<Booking> spec = BookingSpecifications.withCriteria(criteria);
            return bookingRepository.findAll(spec, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error searching bookings: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByUserId(Long userId, PageRequest pageRequest) {
        try {
            getUserOrThrow(userId);
            return bookingRepository.findByUserId(userId, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error retrieving user bookings: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByProviderId(Long providerId, PageRequest pageRequest) {
        try {
            getProviderOrThrow(providerId);
            return bookingRepository.findByProviderId(providerId, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error retrieving provider bookings: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByUserIdAndStatus(
            Long userId, BookingStatus status, PageRequest pageRequest) {
        try {
            getUserOrThrow(userId);
            return bookingRepository.findByUserIdAndStatus(userId, status, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error retrieving user bookings by status: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByProviderIdAndStatus(
            Long providerId, BookingStatus status, PageRequest pageRequest) {
        try {
            getProviderOrThrow(providerId);
            return bookingRepository.findByProviderIdAndStatus(providerId, status, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error retrieving provider bookings by status: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByProviderIdAndStatusAndDateRange(
            Long providerId, BookingStatus status, LocalDateTime start, LocalDateTime end, PageRequest pageRequest) {
        try {
            getProviderOrThrow(providerId);
            validateDateRange(start, end);
            return bookingRepository.findByProviderIdAndStatusAndScheduledStartTimeBetween(
                    providerId, status, start, end, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error retrieving provider bookings by status and date range: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByUserIdAndStatusAndDateRange(
            Long userId, BookingStatus status, LocalDateTime start, LocalDateTime end, PageRequest pageRequest) {
        try {
            getUserOrThrow(userId);
            validateDateRange(start, end);
            return bookingRepository.findByUserIdAndStatusAndScheduledStartTimeBetween(
                    userId, status, start, end, pageRequest.toSpringPageRequest())
                    .map(bookingMapper::toResponse);
        } catch (Exception e) {
            log.error("Error retrieving user bookings by status and date range: ", e);
            throw e;
        }
    }

    // Helper methods
    private UserDto getUserOrThrow(Long userId) {
        try {
            return userClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("User not found with id: " + userId);
        } catch (Exception e) {
            log.error("Error fetching user: ", e);
            throw new ServiceException("Error fetching user details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ProviderDto getProviderOrThrow(Long providerId) {
        try {
            return providerClient.getProviderById(providerId);
        } catch (FeignException.NotFound e) {
            throw new ProviderNotFoundException("Provider not found with id: " + providerId);
        } catch (Exception e) {
            log.error("Error fetching provider: ", e);
            throw new ServiceException("Error fetching provider details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateBookingCreation(UserDto user, ProviderDto provider, CreateBookingRequest request) {
        if (!user.isActive()) {
            throw new BookingValidationException("User account is not active");
        }
        if (!provider.isAvailable()) {
            throw new ProviderUnavailableException("Provider is not available for bookings");
        }
        validateBookingTime(request.getScheduledStartTime(), request.getScheduledEndTime());
        validateProviderAvailability(request.getProviderId(), request.getScheduledStartTime(), request.getScheduledEndTime());
    }

    private Booking buildBooking(CreateBookingRequest request) {
        return Booking.builder()
                .userId(request.getUserId())
                .providerId(request.getProviderId())
                .scheduledStartTime(request.getScheduledStartTime())
                .scheduledEndTime(request.getScheduledEndTime())
                .serviceAddress(request.getServiceAddress())
                .description(request.getDescription())
                .status(BookingStatus.PENDING)
                .build();
    }

    private void validateStatusTransition(BookingStatus currentStatus, BookingStatus newStatus) {
        if (currentStatus == BookingStatus.COMPLETED || 
            currentStatus == BookingStatus.CANCELLED || 
            currentStatus == BookingStatus.NO_SHOW) {
            throw new InvalidBookingStateException(
                    String.format("Cannot modify a %s booking", currentStatus));
        }

        if (currentStatus == BookingStatus.IN_PROGRESS && 
            (newStatus == BookingStatus.PENDING || newStatus == BookingStatus.ACCEPTED)) {
            throw new InvalidBookingStateException(
                    "Cannot change an in-progress booking back to " + newStatus);
        }
    }

    private void handleStatusSpecificActions(Booking booking, BookingStatus newStatus) {
        LocalDateTime now = LocalDateTime.now();
        
        switch (newStatus) {
            case IN_PROGRESS:
                booking.setActualStartTime(now);
                break;
            case COMPLETED:
                booking.setActualEndTime(now);
                break;
            case CANCELLED:
                booking.setActualEndTime(now);
                break;
            case NO_SHOW:
                booking.setActualEndTime(now);
                break;
        }
    }

    private void validateBookingTime(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new BookingValidationException("Start and end times must be provided");
        }
        if (start.isAfter(end)) {
            throw new BookingValidationException("Start time cannot be after end time");
        }
        if (start.isBefore(LocalDateTime.now())) {
            throw new BookingValidationException("Cannot create bookings for past times");
        }
    }

    private void validateDateRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new BookingValidationException("Start and end times must be provided");
        }
        if (start.isAfter(end)) {
            throw new BookingValidationException("Start time cannot be after end time");
        }
    }

    private Booking getBookingOrThrow(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
    }

    private void validateProviderAvailability(Long providerId, LocalDateTime start, LocalDateTime end) {
        List<BookingStatus> conflictingStatuses = Arrays.asList(
                BookingStatus.PENDING,
                BookingStatus.ACCEPTED,
                BookingStatus.IN_PROGRESS
        );
        
        if (bookingRepository.existsOverlappingBooking(providerId, conflictingStatuses, start, end)) {
            throw new ProviderUnavailableException("Provider is not available for the requested time slot");
        }
    }

    // Circuit breaker fallback methods
    private BookingResponse createBookingFallback(CreateBookingRequest request, Exception e) {
        log.error("Circuit breaker fallback: Failed to create booking", e);
        throw new ServiceException("Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private BookingResponse updateBookingStatusFallback(Long id, BookingStatus newStatus, Exception e) {
        log.error("Circuit breaker fallback: Failed to update booking status", e);
        throw new ServiceException("Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private BookingResponse completeServiceFallback(Long id, Double totalCost, Exception e) {
        log.error("Circuit breaker fallback: Failed to complete service", e);
        throw new ServiceException("Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private BookingResponse addRatingAndFeedbackFallback(Long id, Double rating, String feedback, Exception e) {
        log.error("Circuit breaker fallback: Failed to add rating and feedback", e);
        throw new ServiceException("Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
} 