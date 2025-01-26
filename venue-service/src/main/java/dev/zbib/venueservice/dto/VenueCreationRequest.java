package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VenueCreationRequest {
    private UUID ownerId;
    private String name;
    private int totalCapacity;
    private int bookingTimeline;
    private int minBookingDuration;
    private int maxBookingDuration;
}
