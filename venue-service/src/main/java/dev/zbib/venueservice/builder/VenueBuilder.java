package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.entity.Venue;

import java.util.UUID;

public class VenueBuilder {

    public static Venue buildVenue(UUID ownerId, VenueCreationRequest request) {
        return Venue
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .ownerId(ownerId)
                .bookingTimeline(request.getBookingTimeline())
                .build();
    }

    public static VenueCreationResponse buildVenueResponse(Venue venue) {
        return VenueCreationResponse
                .builder()
                .id(venue.getId())
                .ownerId(venue.getOwnerId())
                .name(venue.getName())
                .bookingTimeline(venue.getBookingTimeline())
                .status(venue
                        .getStatus()
                        .getDisplayName())
                .totalCapacity(venue.getMaxCapacity())
                .build();
    }
}
