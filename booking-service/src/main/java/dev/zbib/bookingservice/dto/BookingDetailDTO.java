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
public class BookingDetailDTO {
    private Long id;
    private Long userId;
    private Long providerId;
    private BookingStatus status;
    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;
    private LocalDateTime actualStartTime;
    private LocalDateTime actualEndTime;
    private String serviceAddress;
    private String description;
    private Double totalCost;
    private Double rating;
    private String feedback;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;
    private String providerName;
} 