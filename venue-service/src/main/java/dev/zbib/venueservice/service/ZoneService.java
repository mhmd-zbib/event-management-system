package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.repository.ZoneRepository;
import dev.zbib.venueservice.validator.ZoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.zbib.venueservice.builder.ZoneBuilder.buildZone;
import static dev.zbib.venueservice.builder.ZoneBuilder.buildZoneCreationResponse;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final VenueService venueService;
    private final ZoneValidator zoneValidator;
    private final ImageService imageService;

    public ZoneCreationResponse createZone(UUID venueId, ZoneCreationRequest request) {
        Venue venue = venueService.getVenueById(venueId);
        zoneValidator.validateZoneCreation(request, venue);
        Zone zone = buildZone(request, venue);
        Zone savedZone = zoneRepository.save(zone);
        imageService.createImages(savedZone.getId(), request.getImages(), EntityType.ZONE);
        return buildZoneCreationResponse(savedZone);
    }
}
