package dev.zbib.bookingservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateBookingRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Provider ID is required")
    private Long providerId;

    @NotNull(message = "Service start time is required")
    @Future(message = "Service start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "Service end time is required")
    @Future(message = "Service end time must be in the future")
    private LocalDateTime endTime;

    @NotBlank(message = "Service address is required")
    private String serviceAddress;

    private String description;
} 