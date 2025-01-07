package dev.zbib.bookingservice.dto;

import dev.zbib.shared.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private UUID id;
    private String eventId;
    private String userId;
    private String venueId;
    private String reference;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private String paymentStatus;
    private Double totalPrice;
    private String notes;
}