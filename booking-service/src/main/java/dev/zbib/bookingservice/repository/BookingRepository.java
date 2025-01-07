package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.dto.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT COUNT(*) > 0 FROM bookings b "
            + "WHERE b.venue_id = :venueId "
            + "AND ("
            + "    :newStartTime BETWEEN b.start_time AND b.end_time "
            + "    OR :newEndTime BETWEEN b.start_time AND b.end_time "
            + "    OR :newStartTime <= b.start_time AND :newEndTime >= b.end_time"
            + ")", nativeQuery = true)
    boolean isBookingAvailable(@Param("venueId") String venueId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime);

}
