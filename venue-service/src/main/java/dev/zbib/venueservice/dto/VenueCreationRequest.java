package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueCreationRequest {
    private String name;
    private int totalCapacity;
    private int bookingTimeline;
    private int capacity;
    private int minBookingDuration;
    private int maxBookingDuration;
}
