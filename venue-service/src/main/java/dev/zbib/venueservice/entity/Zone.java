package dev.zbib.venueservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Zone name is required")
    @Size(min = 2, max = 100, message = "Zone name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10000, message = "Capacity cannot exceed 10000")
    @Column(nullable = false)
    private int capacity;

    @NotNull(message = "Status is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusTypes status;

    @NotNull(message = "Length is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Length must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Length cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Length must have at most 6 digits and 2 decimal places")
    @Column(name = "length", precision = 8, scale = 2, nullable = false)
    private BigDecimal length;

    @NotNull(message = "Width is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Width must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Width cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Width must have at most 6 digits and 2 decimal places")
    @Column(name = "width", precision = 8, scale = 2, nullable = false)
    private BigDecimal width;

    @NotNull(message = "Height is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Height must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Height cannot exceed 9999.99")
    @Digits(integer = 4, fraction = 2, message = "Height must have at most 4 digits and 2 decimal places")
    @Column(name = "height", precision = 6, scale = 2, nullable = false)
    private BigDecimal height;

    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Area must be greater than 0")
    @DecimalMax(value = "99999999.99", message = "Area cannot exceed 99999999.99")
    @Digits(integer = 8, fraction = 2, message = "Area must have at most 8 digits and 2 decimal places")
    @Column(name = "area", precision = 10, scale = 2, nullable = false)
    private BigDecimal area;

    @NotNull(message = "Hourly fee is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Hourly fee must be 0 or greater")
    @DecimalMax(value = "999999.99", message = "Hourly fee cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Hourly fee must have at most 6 digits and 2 decimal places")
    @Column(name = "hourly_fee", precision = 8, scale = 2, nullable = false)
    private BigDecimal hourlyFee;

    @NotNull(message = "Excess fee is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Excess fee must be 0 or greater")
    @DecimalMax(value = "999999.99", message = "Excess fee cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Excess fee must have at most 6 digits and 2 decimal places")
    @Column(name = "excess_fee", precision = 8, scale = 2, nullable = false)
    private BigDecimal excessFee;

    @PrePersist
    @PreUpdate
    private void calculateArea() {
        if (length != null && width != null) {
            this.area = length.multiply(width)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

}
