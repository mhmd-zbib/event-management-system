package dev.zbib.listingservice.builder;

import dev.zbib.listingservice.dto.CreateListingRequest;
import dev.zbib.listingservice.dto.ListingListResponse;
import dev.zbib.listingservice.dto.ListingResponse;
import dev.zbib.listingservice.entity.Listing;

public class ListingBuilder {


    public static Listing buildListing(String userId, CreateListingRequest req) {
        return Listing.builder()
                .userId(userId)
                .name(req.getName())
                .description(req.getDescription())
                .category(req.getCategory())
                .price(req.getPrice())
                .stock(req.getStock())
                .build();
    }

    public static ListingResponse buildListingResponse(Listing listing) {
        return ListingResponse.builder()
                .userId(listing.getUserId())
                .id(listing.getId())
                .name(listing.getName())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .available(listing.isAvailable())
                .reservedStock(listing.getReservedStock())
                .category(listing.getCategory())
                .build();
    }

    public static ListingListResponse buildListingListResponse(Listing listing) {
        return ListingListResponse.builder()
                .id(listing.getId())
                .name(listing.getName())
                .price(listing.getPrice())
                .available(listing.isAvailable())
                .category(listing.getCategory())
                .build();
    }
}
