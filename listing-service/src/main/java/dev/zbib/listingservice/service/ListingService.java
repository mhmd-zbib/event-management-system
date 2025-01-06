package dev.zbib.listingservice.service;

import dev.zbib.listingservice.dto.CreateListingRequest;
import dev.zbib.listingservice.dto.ListingResponse;
import dev.zbib.listingservice.entity.Listing;
import dev.zbib.listingservice.exception.ListingNotFoundException;
import dev.zbib.listingservice.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static dev.zbib.listingservice.builder.ListingBuilder.buildListing;
import static dev.zbib.listingservice.builder.ListingBuilder.buildListingResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;

    public void createListing(String userId, CreateListingRequest req) {
        Listing listing = buildListing(userId, req);
        listingRepository.save(listing);
    }

    public ListingResponse getListing(String id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
        return buildListingResponse(listing);
    }

    public Listing getListingEntity(String id) {
        return listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
    }
}
