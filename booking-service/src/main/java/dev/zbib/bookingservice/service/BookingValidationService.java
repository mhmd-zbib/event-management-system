package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.exception.BookingTimeOverlapException;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookingValidationService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ProviderService providerService;

    public void validateBooking(CreateBookingRequest req) {
        Long userId = req.getCustomerId();
        Long providerId = req.getProviderId();

        userService.canBook(userId);
        userService.canBeBooked(providerId);
        providerService.canBeBooked(providerId, req.getServiceType());
        validateTime(providerId, req.getBookingDate());
    }

    private void validateTime(
            Long providerId,
            LocalDateTime bookingTime) {
        if (!bookingRepository.existsByProviderId(providerId)) return;
        if (bookingRepository.hasOverlappingBookings(providerId, bookingTime))
            throw new BookingTimeOverlapException(bookingTime);
    }
}



