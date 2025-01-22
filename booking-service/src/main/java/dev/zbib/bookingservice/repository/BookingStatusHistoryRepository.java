package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.entity.BookingStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingStatusHistoryRepository extends JpaRepository<BookingStatusHistory, UUID> {
}
