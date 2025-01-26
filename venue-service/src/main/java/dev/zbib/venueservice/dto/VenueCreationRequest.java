package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VenueCreationRequest {
    private String name;
    private String description;
    private int maxCapacity;
    private int bookingTimeline;
    private List<ImageRequest> images;
}
