package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.entity.BookingStatusHistory;
import dev.zbib.bookingservice.repository.BookingStatusHistoryRepository;
import dev.zbib.shared.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingStatusHistoryService {

    private final BookingStatusHistoryRepository bookingStatusHistoryRepository;

    public void logStatusChange(
            Booking booking, BookingStatus previousStatus, BookingStatus newStatus, String changedBy, String note) {
        BookingStatusHistory history = BookingStatusHistory.builder()
                .booking(booking)
                .previousStatus(previousStatus)
                .newStatus(newStatus)
                .changedAt(LocalDateTime.now())
                .changedBy(changedBy)
                .note(note)
                .build();
        bookingStatusHistoryRepository.save(history);
    }

    public void logCreatedStatus(Booking booking, String createdBy) {
        logStatusChange(booking, null, BookingStatus.PENDING, createdBy, "Booking created");
    }
}
