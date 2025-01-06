package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.VenueListResponse;
import dev.zbib.venueservice.dto.VenueRequest;
import dev.zbib.venueservice.dto.VenueResponse;
import dev.zbib.venueservice.entity.Venue;

public class VenueBuilder {


    public static Venue buildVenue(String ownerId, VenueRequest venueRequest) {
        return Venue.builder()
                .name(venueRequest.getName())
                .description(venueRequest.getDescription())
                .type(venueRequest.getType())
                .capacity(venueRequest.getCapacity())
                .category(venueRequest.getCategory())
                .ownerId(ownerId)
                .location(venueRequest.getLocation())
                .images(venueRequest.getImages())
                .tags(venueRequest.getTags())
                .available(true)
                .price(venueRequest.getPrice())
                .isFeatured(false)
                .build();
    }

    public static VenueListResponse buildVenueListResponse(Venue venue) {
        return VenueListResponse.builder()
                .id(venue.getId())
                .name(venue.getName())
                .type(venue.getType())
                .capacity(venue.getCapacity())
                .price(venue.getPrice())
                .isFeatured(venue.isFeatured())
                .build();
    }

    public static VenueResponse buildVenueResponse(Venue venue) {
        return VenueResponse.builder()
                .id(venue.getId())
                .name(venue.getName())
                .description(venue.getDescription())
                .type(venue.getType())
                .capacity(venue.getCapacity())
                .category(venue.getCategory())
                .rating(venue.getRating())
                .price(venue.getPrice())
                .ownerId(venue.getOwnerId())
                .available(venue.isAvailable())
                .location(venue.getLocation())
                .tags(venue.getTags())
                .isFeatured(venue.isFeatured())
                .build();
    }

}


