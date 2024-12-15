package dev.zbib.bookingservice.dto;

import dev.zbib.bookingservice.model.BookingStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingRequest {
    @Future(message = "Start time must be in the future")
    private LocalDateTime scheduledStartTime;

    @Future(message = "End time must be in the future")
    private LocalDateTime scheduledEndTime;

    private String serviceAddress;
    private String description;
} 