package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.exception.*;
import dev.zbib.bookingservice.mapper.BookingMapper;
import dev.zbib.bookingservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingService {

    private final BookingValidationService validation;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;


    @Transactional
    public BookingResponse createBooking(CreateBookingRequest req) {
        try {
            validation.validateCreation(req);
            Booking booking = bookingMapper.toBooking(req);
            Booking createBooking = bookingRepository.save(booking);
            return bookingMapper.toBookingResponse(createBooking);
        } catch (CustomerNotEligibleException | ProviderNotEligibleException e) {
            log.warn("Eligibility check failed: {} with {}", e.getMessage(), e.getDetails());
            throw e;
        } catch (BookingTimeOverlapException e) {
            log.warn("Booking time overlap detected: {}", e.getMessage());
            throw e;
        } catch (ProviderDetailsMismatchException e) {
            log.warn("Provider details mismatch: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while creating booking: {}", e.getMessage(), e);
            throw new BookingException("An unexpected error occurred during booking creation");
        }
    }

    public BookingResponse getBooking(Long id) {
        return bookingRepository.findResponseById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }
}
