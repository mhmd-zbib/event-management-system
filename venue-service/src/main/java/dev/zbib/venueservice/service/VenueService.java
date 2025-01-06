package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueRequest;
import dev.zbib.venueservice.dto.VenueResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.exception.ListingNotFoundException;
import dev.zbib.venueservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static dev.zbib.venueservice.builder.ListingBuilder.buildListing;
import static dev.zbib.venueservice.builder.ListingBuilder.buildListingResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public void createListing(String userId, VenueRequest req) {
        Venue venue = buildListing(userId, req);
        venueRepository.save(venue);
    }

    public VenueResponse getListing(String id) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
        return buildListingResponse(venue);
    }

    public Venue getListingEntity(String id) {
        return venueRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
    }
}
