package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.BookingRepository;
import dev.zbib.bookingservice.model.entity.Booking;
import dev.zbib.bookingservice.model.enums.BookingStatus;
import dev.zbib.bookingservice.model.request.CreateDirectBookingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingService {

    private final BookingRepository bookingRepository;

    public Long createDirectBooking(CreateDirectBookingRequest req) {
        log.info("Starting direct booking creation");
        log.info("Checking customer's info");
        log.info("Checking provider's info");
        log.info("Matching provider's job with service booked");
        log.info("Checking provider's schedule");


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
