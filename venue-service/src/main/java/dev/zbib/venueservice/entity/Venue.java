package dev.zbib.venueservice.entity;

import dev.zbib.venueservice.enums.VenueStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @NotBlank(message = "Venue name is required")
    @Size(min = 2, max = 100, message = "Venue name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Total capacity is required")
    @Min(value = 1, message = "Total capacity must be at least 1")
    @Max(value = 100000, message = "Total capacity cannot exceed 100,000")
    @Column(name = "total_capacity", nullable = false)
    private int maxCapacity;

    @NotNull(message = "Booking timeline is required")
    @Min(value = 1, message = "Booking timeline must be at least 1")
    @Max(value = 365, message = "Booking timeline cannot exceed 365 days")
    @Column(name = "booking_timeline", nullable = false)
    private int bookingTimeline;

    @NotNull(message = "Status is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusTypes status;

    private BigDecimal rating;
}