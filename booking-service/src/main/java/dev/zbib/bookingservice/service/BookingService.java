package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.request.CreateDirectBookingRequest;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.enums.BookingStatus;
import dev.zbib.bookingservice.repository.BookingRepository;
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

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }


    public void acceptBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.ACCEPTED);
    }

    public void declineBooking(
            Long id,
            String reason) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.DECLINED);
    }

    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CANCELED);
    }
}
