package dev.zbib.bookingservice.entity;

import dev.zbib.shared.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "provider_id")
    private Long providerId;
    
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String paymentStatus;

    private Double amount;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = BookingStatus.PENDING;
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

} 