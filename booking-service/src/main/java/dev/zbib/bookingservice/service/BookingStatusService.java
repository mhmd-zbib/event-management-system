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

    private static final BookingStatus ACCEPTED = BookingStatus.ACCEPTED;
    private static final BookingStatus REJECTED = BookingStatus.REJECTED;
    private static final BookingStatus CANCELLED_BY_BOOKER = BookingStatus.CANCELLED_BY_BOOKER;
    private static final BookingStatus CANCELLED_BY_OWNER = BookingStatus.CANCELLED_BY_OWNER;
    private static final BookingStatus CONFIRMED = BookingStatus.CONFIRMED;

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private final BookingStatusHistoryService statusHistoryService;

    public void setBookingStatus(UUID bookingId, BookingStatus newStatus, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        if (booking.getStatus() == newStatus) throw new IllegalArgumentException("Invalid action: " + newStatus);
        if (!booking.getStatus().canTransitionTo(newStatus))
            throw new IllegalArgumentException("Invalid action: " + newStatus);

        Booking updatedBooking = switch (newStatus) {
            case PENDING -> null;
            case ACCEPTED -> handleAccept(booking, userId);
            case REJECTED -> handleReject(booking, userId, note);
            case CANCELLED_BY_OWNER -> handleCancelledByOwner(booking, userId, note);
            case CANCELLED_BY_BOOKER -> handleCancelledByBooker(booking, userId, note);
            case CONFIRMED -> handleConfirm(booking, userId);
            case COMPLETED -> handleCompleted(booking, userId);
            default -> throw new IllegalArgumentException("Invalid action: " + newStatus);
        };

        statusHistoryService.logStatusChange(updatedBooking, booking.getStatus(), newStatus, userId, note);
    }

    private Booking handleCompleted(Booking booking, String userId) {
        validateOwner(booking, userId);
        booking.setStatus(BookingStatus.COMPLETED);
        return bookingRepository.save(booking);
    }

    private Booking handleAccept(Booking booking, String userId) {
        validateOwner(booking, userId);
        booking.setStatus(ACCEPTED);
        return bookingRepository.save(booking);
    }

    private Booking handleReject(Booking booking, String userId, String note) {
        validateOwner(booking, userId);
        booking.setStatus(REJECTED);
        return bookingRepository.save(booking);
    }

    private Booking handleCancelledByOwner(Booking booking, String userId, String note) {
        validateOwner(booking, userId);
        booking.setStatus(CANCELLED_BY_OWNER);
        return bookingRepository.save(booking);
    }

    private Booking handleCancelledByBooker(Booking booking, String userId, String note) {
        validateBooker(booking, userId);
        booking.setStatus(CANCELLED_BY_BOOKER);
        return bookingRepository.save(booking);
    }

    private Booking handleConfirm(Booking booking, String userId) {
        validateOwner(booking, userId);
        booking.setStatus(CONFIRMED);
        return bookingRepository.save(booking);
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
