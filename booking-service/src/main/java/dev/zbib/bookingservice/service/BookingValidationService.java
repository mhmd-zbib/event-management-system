package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.ProviderClient;
import dev.zbib.bookingservice.client.UserClient;
import dev.zbib.shared.enums.BookingStatus;
import dev.zbib.bookingservice.exception.BookingValidationException;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingValidationService {

    private static final int MAX_BOOKING_HOURS_IN_ADVANCE = 720; 
    private static final int MIN_BOOKING_HOURS_IN_ADVANCE = 2;
    private static final int MAX_PENDING_BOOKINGS = 3;
    private static final List<BookingStatus> ACTIVE_STATUSES = List.of(BookingStatus.PENDING, BookingStatus.ACCEPTED);
    private final UserClient userClient;
    private final ProviderClient providerClient;
    private final BookingRepository bookingRepository;

    public void validateBookingEligibility(
            Long userId,
            Long providerId,
            LocalDateTime bookingDate) {
        validateUser(userId);
        validateProvider(providerId);
        validateBookingTime(bookingDate);
        validateNoOverlappingBookings(providerId, bookingDate);
        validatePendingBookingsLimit(userId);
        validateNoExistingBooking(userId, providerId);
    }

    private void validateUser(Long userId) {
        UserDetailsResponse userDetails = userClient.getUserDetails(userId);
        if (!userDetails.isActive()) {
            log.warn("Attempt to book with inactive user account: {}", userId);
            throw new BookingValidationException("User account is not active");
        }
    }

    private void validateProvider(Long providerId) {
        boolean providerAvailable = providerClient.checkProviderAvailability(providerId);
        if (!providerAvailable) {
            log.warn("Attempt to book with unavailable provider: {}", providerId);
            throw new BookingValidationException("Provider is not available for booking");
        }
    }

    private void validateBookingTime(LocalDateTime bookingDate) {
        LocalDateTime now = LocalDateTime.now();
        long hoursUntilBooking = ChronoUnit.HOURS.between(now, bookingDate);

        if (hoursUntilBooking < MIN_BOOKING_HOURS_IN_ADVANCE) {
            throw new BookingValidationException(
                    String.format("Booking must be made at least %d hours in advance", MIN_BOOKING_HOURS_IN_ADVANCE));
        }

        if (hoursUntilBooking > MAX_BOOKING_HOURS_IN_ADVANCE) {
            throw new BookingValidationException(
                    String.format(
                            "Booking cannot be made more than %d hours in advance",
                            MAX_BOOKING_HOURS_IN_ADVANCE));
        }
    }

    private void validateNoOverlappingBookings(
            Long providerId,
            LocalDateTime bookingDate) {
        LocalDateTime startWindow = bookingDate.minusHours(1);
        LocalDateTime endWindow = bookingDate.plusHours(1);

        boolean hasOverlap = bookingRepository.existsBookingInTimeRange(
                providerId,
                startWindow,
                endWindow,
                ACTIVE_STATUSES
        );

        if (hasOverlap) {
            throw new BookingValidationException("Provider already has a booking scheduled during this time slot");
        }
    }

    private void validatePendingBookingsLimit(Long userId) {
        long pendingBookingsCount = bookingRepository.countBookingsByFilters(
                userId,
                null,
                BookingStatus.PENDING,
                null
        );

        if (pendingBookingsCount >= MAX_PENDING_BOOKINGS) {
            throw new BookingValidationException(
                    String.format("You have reached the maximum limit of %d pending bookings", MAX_PENDING_BOOKINGS));
        }
    }

    private void validateNoExistingBooking(
            Long userId,
            Long providerId) {
        long activeBookingsCount = bookingRepository.countBookingsByFilters(
                userId,
                providerId,
                null,
                ACTIVE_STATUSES
        );

        if (activeBookingsCount > 0) {
            throw new BookingValidationException("You already have an active booking with this provider");
        }
    }
}