package dev.zbib.bookingservice.dto;

import dev.zbib.shared.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingCreationRequest {
    private String eventId;
    private String venueId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private String bookingReference;
    private String notes;
} 