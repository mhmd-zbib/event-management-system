package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueRequest;
import dev.zbib.venueservice.dto.VenueResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static dev.zbib.venueservice.builder.VenueBuilder.buildVenue;
import static dev.zbib.venueservice.builder.VenueBuilder.buildVenueResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public void createVenue(String userId, VenueRequest req) {
        Venue venue = buildVenue(userId, req);
        venueRepository.save(venue);
    }

    public VenueResponse getVenue(String id) {
        Venue venue = venueRepository.findById(id).orElseThrow(null);
        return buildVenueResponse(venue);
    }

    public boolean existsById(String venueId) {
        return venueRepository.existsById(venueId);
    }
}
