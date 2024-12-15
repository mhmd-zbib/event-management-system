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
public class BookingFilterCriteria {
    private Long userId;
    private Long providerId;
    private BookingStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double minRating;
    private Double maxRating;
    private Double minCost;
    private Double maxCost;
} 