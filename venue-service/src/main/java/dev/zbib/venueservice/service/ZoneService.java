package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.repository.ZoneRepository;
import dev.zbib.venueservice.validator.ZoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final ZoneAmenityService zoneAmenityService;
    private final AmenityService amenityService;

    public ZoneCreationResponse createZone(UUID venueId, ZoneCreationRequest request) {
        Venue venue = venueService.getVenueById(venueId);
        List<Amenity> amenities = amenityService.getAmenitiesById(request.getAmenitiesId());

        zoneValidator.validateZoneCreation(request, venue, amenities);

        Zone zone = buildZone(request, venue);
        Zone savedZone = zoneRepository.save(zone);

        zoneAmenityService.createZoneAmenities(savedZone, amenities);
        imageService.createImages(savedZone.getId(), request.getImages(), EntityType.ZONE);

        return buildZoneCreationResponse(savedZone);
    }
}
