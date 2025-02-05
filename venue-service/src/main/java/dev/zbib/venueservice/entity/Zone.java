package dev.zbib.venueservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Data
@Table(name = "zones")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Zone {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false,
            length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",
                nullable = false)
    private StatusTypes status;

    @Column(name = "length",
            precision = 8,
            scale = 2,
            nullable = false)
    private BigDecimal length;

    @Column(name = "width",
            precision = 8,
            scale = 2,
            nullable = false)
    private BigDecimal width;

    @Column(name = "height",
            precision = 6,
            scale = 2,
            nullable = false)
    private BigDecimal height;

    @Column(name = "area",
            precision = 10,
            scale = 2,
            nullable = false)
    private BigDecimal area;

    @Column(name = "hourly_fee",
            precision = 8,
            scale = 2,
            nullable = false)
    private BigDecimal hourlyFee;

    @Column(name = "excess_fee",
            precision = 8,
            scale = 2,
            nullable = false)
    private BigDecimal excessFee;

    @Column(name = "min_booking_duration",
            nullable = false)
    private int minBookingDuration;

    @Column(name = "max_booking_duration",
            nullable = false)
    private int maxBookingDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id",
                nullable = false)
    private Venue venue;

    @PrePersist
    @PreUpdate
    private void calculateArea() {
        if (length != null && width != null) {
            this.area = length
                    .multiply(width)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }
}