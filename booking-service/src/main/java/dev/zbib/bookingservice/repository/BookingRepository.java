package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
