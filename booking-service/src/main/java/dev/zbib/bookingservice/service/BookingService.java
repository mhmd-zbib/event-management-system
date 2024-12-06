package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.model.request.CreateDirectBookingRequest;
import dev.zbib.bookingservice.model.entity.Booking;
import dev.zbib.shared.enums.BookingStatus;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                .createdAt(LocalDateTime.now())
                .bookingDate(req.getBookingDate()
                        .toLocalDateTime())
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
        bookingRepository.save(booking);
    }

    public void declineBooking(
            Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.DECLINED);
        bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);
    }
}
