package dev.zbib.venueservice.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueCreationResponse {
    private UUID id;
    private UUID ownerId;
    private String name;
    private String status;
    private int totalCapacity;
    private int bookingTimeline;
    private int minBookingDuration;
    private int maxBookingDuration;
}
