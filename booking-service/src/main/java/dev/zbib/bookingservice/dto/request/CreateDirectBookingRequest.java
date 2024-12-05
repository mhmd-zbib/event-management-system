package dev.zbib.bookingservice.dto.request;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class CreateDirectBookingRequest {
    private Long userId;
    private Long providerId;
    private Long serviceId;
    private ZonedDateTime bookingDate;
    private String title;
    private String description;
    private String additionalInfo;
    private List<String> photos;
}
