package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.repository.VenueRepository;
import dev.zbib.venueservice.validator.VenueValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.zbib.venueservice.builder.VenueBuilder.buildVenue;
import static dev.zbib.venueservice.builder.VenueBuilder.buildVenueResponse;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueValidator venueValidator;
    private final ImageService imageService;

    public VenueCreationResponse createVenue(UUID userId, VenueCreationRequest request) {
        venueValidator.validateVenueCreation(request);
        Venue venue = buildVenue(userId, request);
        Venue savedVenue = venueRepository.save(venue);
        imageService.createImages(savedVenue.getId(), request.getImages(), EntityType.VENUE);
        return buildVenueResponse(savedVenue);
    }

    public Venue getVenueById(UUID venueId) {
        return venueRepository
                .findById(venueId)
                .orElse(null);
    }
}
