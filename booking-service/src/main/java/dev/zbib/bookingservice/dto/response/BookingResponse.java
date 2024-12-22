package dev.zbib.bookingservice.dto.response;

import dev.zbib.shared.enums.BookingStatus;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        Long customerId,
        Long providerId,
        LocalDateTime bookingDate,
        BookingStatus status,
        LocalDateTime createdAt,
        String paymentStatus,
        Double amount,
        LocalDateTime updatedAt
) {
}