package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.enums.BookingStatus;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingStatusService {
    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private final BookingStatusHistoryService statusHistoryService;

    public void setBookingStatus(UUID bookingId, BookingStatus newStatus, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        switch (newStatus) {
            case REJECTED -> handleReject(booking, userId, note);
            case CANCELLED_BY_OWNER -> handleCancelledByOwner(booking, userId, note);
            case CANCELLED_BY_BOOKER -> handleCancelledByBooker(booking, userId, note);
            case CONFIRMED -> handleConfirm(booking, userId);
            case COMPLETED -> handleCompleted(booking, userId);
            default -> throw new IllegalArgumentException("Invalid action: " + newStatus);
        }
        ;
        booking.setStatus(BookingStatus.COMPLETED);
        Booking updatedBooking = bookingRepository.save(booking);
        statusHistoryService.logStatusChange(updatedBooking, booking.getStatus(), newStatus, userId, note);
    }

    private void handleCompleted(Booking booking, String userId) {
        validateOwner(booking, userId);

    }

    private void handleReject(Booking booking, String userId, String note) {
        validateOwner(booking, userId);

    }

    private void handleCancelledByOwner(Booking booking, String userId, String note) {
        validateOwner(booking, userId);

    }

    private void handleCancelledByBooker(Booking booking, String userId, String note) {
        validateBooker(booking, userId);

    }

    private void handleConfirm(Booking booking, String userId) {
        validateOwner(booking, userId);

    }

    private void validateOwner(Booking booking, String userId) {
        if (!Objects.equals(booking.getVenueOwnerId(), userId))
            throw new IllegalArgumentException("Invalid owner: " + booking.getVenueOwnerId());
    }

    private void validateBooker(Booking booking, String userId) {
        if (!Objects.equals(booking.getUserId(), userId))
            throw new IllegalArgumentException("Invalid user: " + booking.getUserId());
    }
}
