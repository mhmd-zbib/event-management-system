package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.shared.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) > 0 FROM Booking b " +
            "WHERE b.providerId = :providerId " +
            "AND b.bookingDate BETWEEN :startTime AND :endTime " +
            "AND b.status IN :statuses")
    boolean existsBookingInTimeRange(
            @Param("providerId") Long providerId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("statuses") List<BookingStatus> statuses
    );

    @Query("SELECT COUNT(b) FROM Booking b " +
            "WHERE b.userId = :userId " +
            "AND (:providerId IS NULL OR b.providerId = :providerId) " +
            "AND (:status IS NULL OR b.status = :status) " +
            "AND (:statuses IS NULL OR b.status IN :statuses)")
    long countBookingsByFilters(
            @Param("userId") Long userId,
            @Param("providerId") Long providerId,
            @Param("status") BookingStatus status,
            @Param("statuses") List<BookingStatus> statuses
    );
}
