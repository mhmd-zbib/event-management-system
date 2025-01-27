package dev.zbib.venueservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ZoneCreationRequest {
    private String name;
    private String description;
    private int capacity;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal hourlyFee;
    private BigDecimal excessFee;
    private int minBookingDuration;
    private int maxBookingDuration;
}
