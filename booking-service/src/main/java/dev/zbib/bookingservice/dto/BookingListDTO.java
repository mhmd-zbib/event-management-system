package dev.zbib.bookingservice.dto;

import dev.zbib.bookingservice.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingListDTO {
    private Long id;
    private Long userId;
    private Long providerId;
    private BookingStatus status;
    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;
    private String serviceAddress;
    private Double totalCost;
    private Double rating;
} 