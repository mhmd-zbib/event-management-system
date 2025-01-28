package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "Request object for creating a new zone")
public class ZoneCreationRequest {
    @Schema(description = "Name of the zone", example = "VIP Lounge", required = true)
    private String name;

    @Schema(description = "Detailed description of the zone", example = "Premium lounge area with scenic view")
    private String description;

    @Schema(description = "Maximum capacity of people", example = "50", required = true)
    private int capacity;

    @Schema(description = "Length of the zone in meters", example = "10.5")
    private BigDecimal length;

    @Schema(description = "Width of the zone in meters", example = "8.0")
    private BigDecimal width;

    @Schema(description = "Height of the zone in meters", example = "3.0")
    private BigDecimal height;

    @Schema(description = "Hourly fee for booking", example = "100.00")
    private BigDecimal hourlyFee;

    @Schema(description = "Fee charged for exceeding booking time", example = "150.00")
    private BigDecimal excessFee;

    @Schema(description = "Minimum booking duration in hours", example = "2")
    private int minBookingDuration;

    @Schema(description = "Maximum booking duration in hours", example = "8")
    private int maxBookingDuration;

    @Schema(description = "List of images associated with the zone")
    private List<ImageCreationRequest> images;
}