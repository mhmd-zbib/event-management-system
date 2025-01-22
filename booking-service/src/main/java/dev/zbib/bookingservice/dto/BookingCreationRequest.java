package dev.zbib.bookingservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingCreationRequest {
    private String eventId;
    private String venueId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String notes;
} 