package dev.zbib.bookingservice.dto.request;

import dev.zbib.shared.enums.ServiceType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private LocalDateTime bookingTime;

    @NotBlank(message = "Service address is required")
    private String serviceAddress;

    private ServiceType serviceType;

    private String description;
} 