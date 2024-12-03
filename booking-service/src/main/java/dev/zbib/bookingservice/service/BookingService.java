package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.BookingRepository;
import dev.zbib.bookingservice.dto.request.CreateDirectBookingRequest;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;

    public Long createDirectBooking(CreateDirectBookingRequest req) {
        userService.verifyUserStatus(req.getUserId());
        userService.verifyProviderStatus(req.getProviderId());

        Booking booking = Booking.builder()
                .userId(req.getUserId())
                .providerId(req.getProviderId())
                .serviceId(req.getServiceId())
                .title(req.getTitle())
                .description(req.getDescription())
                .additionalInfo(req.getAdditionalInfo())
                .status(BookingStatus.PENDING)
                .createAt(Instant.now())
                .bookingDate(req.getBookingDate())
                .build();
        return bookingRepository.save(booking)
                .getId();
    }
}
