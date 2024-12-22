package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<BookingResponse> findBookingById(Long id);

    @Query(value = "SELECT COUNT(*) > 0 FROM bookings b " +
            "WHERE b.provider_id = :providerId " +
            "AND b.booking_date = :bookingTime", nativeQuery = true)
    boolean hasOverlappingBookings(
            Long providerId,
            LocalDateTime bookingTime);

    boolean existsByProviderId(Long providerId);

}
