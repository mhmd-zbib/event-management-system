package dev.zbib.venueservice.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueCreationResponse {
    private UUID venueId;
    private UUID ownerId;
    private String name;
    private String status;
    private String statusDescription;
    private int totalCapacity;
    private int bookingTimeline;
    private int minBookingDuration;
    private int maxBookingDuration;
}
