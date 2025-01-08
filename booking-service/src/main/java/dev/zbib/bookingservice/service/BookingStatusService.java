package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.enums.BookingStatus;
import dev.zbib.bookingservice.exception.BookingTimeOverlapException;
import dev.zbib.bookingservice.repository.BookingRepository;
import dev.zbib.shared.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingStatusService {

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private final BookingStatusHistoryService statusHistoryService;


    public void setBookingStatus(UUID bookingId, BookingStatus newStatus, String userId, String note) {
        Booking booking = bookingService.getBookingEntity(bookingId);
        BookingStatus currentStatus = booking.getStatus();

        UserRole userRole = getUserRole(booking, userId);
        validateStatusTransition(currentStatus, newStatus, bookingId, userRole);
        if (currentStatus == newStatus) {
            log.debug("No status change needed for booking: {}", bookingId);
            return;
        }
        statusHistoryService.logStatusChange(booking, currentStatus, newStatus, userId, note);
        updateBookingStatus(booking, newStatus);
    }

    private UserRole getUserRole(Booking booking, String userId) {
        if (booking.getUserId().equals(userId)) {
            return UserRole.CUSTOMER;
        }
        if (booking.getVenueOwnerId().equals(userId)) {
            return UserRole.CUSTOMER;
        }
        throw new UserNotAssociatedWithBookingException();
    }

    private void validateStatusTransition(BookingStatus currentStatus, BookingStatus newStatus, UUID bookingId, UserRole userRole) {
        if (!currentStatus.canTransitionTo(newStatus, userRole)) {
            throw new BookingStatusChangeException(bookingId);
        }
    }

    private void updateBookingStatus(Booking booking, BookingStatus newStatus) {
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
        String responsibleUserId = getResponsibleUserId(booking, newStatus);
    }

    private String getResponsibleUserId(Booking booking, BookingStatus newStatus) {
        if (newStatus == BookingStatus.CANCELLED_BY_BOOKER || newStatus == BookingStatus.PENDING) {
            return booking.getUserId();
        } else {
            return booking.getVenueId();
        }
    }
}
