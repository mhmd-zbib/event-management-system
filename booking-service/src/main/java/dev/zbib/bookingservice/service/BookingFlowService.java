package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.repository.BookingRepository;
import dev.zbib.shared.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingFlowService {

    private final BookingStatus PENDING = BookingStatus.PENDING;
    private final BookingStatus REJECTED = BookingStatus.REJECTED;
    private final BookingStatus CANCELLED_BY_BOOKER = BookingStatus.CANCELLED_BY_BOOKER;
    private final BookingStatus CANCELLED_BY_OWNER = BookingStatus.CANCELLED_BY_OWNER;
    private final BookingStatus CONFIRMED = BookingStatus.CONFIRMED;


    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private BookingStatusHistoryService statusHistoryService;


    private void changeBookingStatus(UUID bookingId, BookingStatus newStatus, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        BookingStatus currentStatus = booking.getStatus();
        if (currentStatus == newStatus) {
            return;
        }
        statusHistoryService.logStatusChange(booking, currentStatus, newStatus, userId, note);
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
    }


    public void confirmBooking(UUID bookingId, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        BookingStatus currentStatus = booking.getStatus();
        if (!currentStatus.canTransitionTo(CONFIRMED)) throw new BookingStatusChangeException(bookingId);
        changeBookingStatus(bookingId, CONFIRMED, userId, note);
        // notify the booker
    }

    public void rejectBooking(UUID bookingId, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        BookingStatus currentStatus = booking.getStatus();

        if (currentStatus == CONFIRMED) throw new BookingStatusChangeException(id);
        if (currentStatus == REJECTED) throw new BookingStatusChangeException(id);
        booking.setStatus(BookingStatus.REJECTED);
        statusHistoryService.logStatusChange(booking, currentStatus, CONFIRMED, userId, note);
        bookingRepository.save(booking);
        //  notify
    }

    public void cancelBookingByOwner(UUID bookingId, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        BookingStatus currentStatus = booking.getStatus();

        if (currentStatus != CONFIRMED) throw new BookingStatusChangeException();
        booking.setStatus(CANCELLED_BY_OWNER);
        statusHistoryService.logStatusChange(booking, currentStatus, CONFIRMED, userId, note);
        bookingRepository.save(booking);
    }

    public void cancelBookingByBooker(UUID bookingId, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        BookingStatus currentStatus = booking.getStatus();

        if (currentStatus != CONFIRMED && currentStatus != PENDING)
            throw new BookingStatusChangeException(id);
        booking.setStatus(BookingStatus.CANCELLED_BY_BOOKER);
        bookingRepository.save(booking);
        if (currentStatus == CONFIRMED) {
            // make refund
        }
        // notify owner
    }
}
