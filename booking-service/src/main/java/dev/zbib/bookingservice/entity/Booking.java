package dev.zbib.bookingservice.entity;

import dev.zbib.bookingservice.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long providerId;
    private Long serviceId;
    private LocalDateTime bookingDate;
    private LocalDateTime createdAt;
    private String title;
    private String description;
    private String additionalInfo;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
