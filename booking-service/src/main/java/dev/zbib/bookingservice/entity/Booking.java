package dev.zbib.bookingservice.entity;

import dev.zbib.shared.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "bookings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private UUID id;
    private String eventId;
    private String userId;
    private String venueId;
    private String reference;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private String paymentStatus;
    private Double totalPrice;
    private String notes;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingStatusHistory> statusHistory;
} 