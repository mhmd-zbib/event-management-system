package dev.zbib.bookingservice.dto.response;

import dev.zbib.shared.enums.BookingStatus;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        Long userId,
        Long providerId,
        BookingStatus status,
        LocalDateTime scheduledStartTime,
        LocalDateTime scheduledEndTime,
        LocalDateTime actualStartTime,
        LocalDateTime actualEndTime,
        String serviceAddress,
        String description,
        Double totalCost,
        Double rating,
        String feedback,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}