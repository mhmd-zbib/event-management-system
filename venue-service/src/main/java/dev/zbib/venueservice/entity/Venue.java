package dev.zbib.venueservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "venues")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Venue {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "total_capacity", nullable = false)
    private int maxCapacity;

    @Column(name = "booking_timeline", nullable = false)
    private int bookingTimeline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusTypes status;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;
}