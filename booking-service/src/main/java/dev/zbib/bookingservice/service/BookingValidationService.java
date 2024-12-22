package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.exception.BookingTimeOverlapException;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookingValidationService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ProviderService providerService;

    public void validateBooking(CreateBookingRequest req) {
        Long userId = req.getUserId();
        Long providerId = req.getProviderId();

        userService.canBook(userId);
        userService.canBeBooked(providerId);
        providerService.canBeBooked(providerId, req.getServiceType());
        validateTime(providerId, req.getBookingTime());
    }

    private void validateTime(
            Long providerId,
            ZonedDateTime bookingTime) {
        if (!bookingRepository.existsByProviderId(providerId)) return;
        LocalDateTime localDateTime = bookingTime.withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
        if (bookingRepository.hasOverlappingBookings(providerId, localDateTime))
            throw new BookingTimeOverlapException(localDateTime);
    }
}



