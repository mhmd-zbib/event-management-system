package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VenueCreationRequest {
    private String name;
    private String description;
    private int maxCapacity;
    private int bookingTimeline;

    @Getter
    @Setter
    private static class ImageRequest {
        private UUID entityId;
        private String url;
        private int sort_order;
        private int size;
        private int width;
        private int height;
    }
}
