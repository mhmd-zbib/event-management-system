package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.entity.Booking;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("SELECT b FROM Booking b WHERE b.venueId = :venueId "
            + "AND ("
            + "    :newStartTime BETWEEN b.startDate AND b.endDate "
            + "    OR :newEndTime BETWEEN b.startDate AND b.endDate "
            + "    OR :newStartTime <= b.startDate AND :newEndTime >= b.endDate"
            + ")")
    List<Booking> findOverlappingBookings(@Param("venueId") String venueId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime);


}
