package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.zbib.venueservice.builder.VenueBuilder.buildVenue;
import static dev.zbib.venueservice.builder.VenueBuilder.buildVenueResponse;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueCreationResponse createVenue(UUID userId, VenueCreationRequest request) {
        Venue venue = buildVenue(userId, request);
        Venue savedVenue = venueRepository.save(venue);
        return buildVenueResponse(savedVenue);
    }

}
