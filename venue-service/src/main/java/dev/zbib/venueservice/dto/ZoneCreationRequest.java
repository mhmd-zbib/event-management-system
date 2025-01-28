package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "Request object for creating a new zone")
public class ZoneCreationRequest {
    @NotBlank(message = "Zone name is required")
    @Size(min = 2, max = 100, message = "Zone name must be between 2 and 100 characters")
    @Schema(description = "Name of the zone", example = "VIP Lounge", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Schema(description = "Detailed description of the zone", example = "Premium lounge area with scenic view")
    private String description;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10000, message = "Capacity cannot exceed 10000")
    @Schema(description = "Maximum capacity of people", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private int capacity;

    @NotNull(message = "Length is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Length must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Length cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Length must have at most 6 digits and 2 decimal places")
    @Schema(description = "Length of the zone in meters", example = "10.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal length;

    @NotNull(message = "Width is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Width must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Width cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Width must have at most 6 digits and 2 decimal places")
    @Schema(description = "Width of the zone in meters", example = "8.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal width;

    @NotNull(message = "Height is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Height must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Height cannot exceed 9999.99")
    @Digits(integer = 4, fraction = 2, message = "Height must have at most 4 digits and 2 decimal places")
    @Schema(description = "Height of the zone in meters", example = "3.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal height;

    @NotNull(message = "Hourly fee is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Hourly fee must be 0 or greater")
    @DecimalMax(value = "999999.99", message = "Hourly fee cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Hourly fee must have at most 6 digits and 2 decimal places")
    @Schema(description = "Hourly fee for booking", example = "100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal hourlyFee;

    @NotNull(message = "Excess fee is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Excess fee must be 0 or greater")
    @DecimalMax(value = "999999.99", message = "Excess fee cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Excess fee must have at most 6 digits and 2 decimal places")
    @Schema(description = "Fee charged for exceeding booking time",
            example = "150.00",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal excessFee;

    @NotNull(message = "Minimum booking duration is required")
    @Min(value = 1, message = "Minimum booking duration must be at least 1")
    @Max(value = 72, message = "Minimum booking duration cannot exceed 72 hours")
    @Schema(description = "Minimum booking duration in hours",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int minBookingDuration;

    @NotNull(message = "Maximum booking duration is required")
    @Min(value = 1, message = "Maximum booking duration must be at least 1")
    @Max(value = 168, message = "Maximum booking duration cannot exceed 168 hours (1 week)")
    @Schema(description = "Maximum booking duration in hours",
            example = "8",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int maxBookingDuration;

    @Size(min = 3, max = 10, message = "Number of images must be between 3 and 10")
    @NotNull(message = "Images are required")
    @Valid
    @Schema(description = "List of images associated with the zone",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 10,
            minLength = 3)
    private List<ImageCreationRequest> images;
}