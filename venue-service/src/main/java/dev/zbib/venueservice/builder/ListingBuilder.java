package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.VenueRequest;
import dev.zbib.venueservice.dto.VenueListResponse;
import dev.zbib.venueservice.dto.VenueResponse;
import dev.zbib.venueservice.entity.Venue;

public class ListingBuilder {


    public static Venue buildListing(String userId, VenueRequest req) {
        return Venue.builder()
                .ownerId(userId)
                .name(req.getName())
                .description(req.getDescription())
                .category(req.getCategory())
                .price(req.getPrice())
                .stock(req.getStock())
                .build();
    }

    public static VenueResponse buildListingResponse(Venue venue) {
        return VenueResponse.builder()
                .userId(venue.getOwnerId())
                .id(venue.getId())
                .name(venue.getName())
                .description(venue.getDescription())
                .price(venue.getPrice())
                .available(venue.isAvailable())
                .reservedStock(venue.getReservedStock())
                .category(venue.getCategory())
                .build();
    }

    public static VenueListResponse buildListingListResponse(Venue venue) {
        return VenueListResponse.builder()
                .id(venue.getId())
                .name(venue.getName())
                .price(venue.getPrice())
                .available(venue.isAvailable())
                .category(venue.getCategory())
                .build();
    }
}
