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
                .ownerId(ownerId)
                .bookingTimeline(request.getBookingTimeline())
                .maxBookingDuration(request.getMaxBookingDuration())
                .minBookingDuration(request.getMinBookingDuration())
                .build();
    }

    public static VenueCreationResponse buildVenueResponse(Venue venue) {
        return VenueCreationResponse
                .builder()
                .id(venue.getId())
                .name(venue.getName())
                .bookingTimeline(venue.getBookingTimeline())
                .maxBookingDuration(venue.getMaxBookingDuration())
                .minBookingDuration(venue.getMinBookingDuration())
                .status(venue
                        .getStatus()
                        .getDisplayName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }
}
