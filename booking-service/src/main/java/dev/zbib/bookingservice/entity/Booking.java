package dev.zbib.bookingservice.entity;

import dev.zbib.bookingservice.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long providerId;
    private Long serviceId;
    private Instant bookingDate;
    private Instant createAt;
    private String title;
    private String description;
    private String additionalInfo;
    private BookingStatus status;

}
