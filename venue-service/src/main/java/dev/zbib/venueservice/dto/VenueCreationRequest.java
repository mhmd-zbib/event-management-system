package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueCreationRequest {
    private String name;
    private String description;
    private int maxCapacity;
    private int bookingTimeline;
}
