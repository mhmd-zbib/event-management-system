package dev.zbib.venueservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueCreationRequest {
    private String name;
    private String description;
    private int maxCapacity;
    private int bookingTimeline;
    private List<ImageCreationRequest> images;
}
