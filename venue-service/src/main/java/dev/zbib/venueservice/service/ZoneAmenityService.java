package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.UpdateZoneAmenityRequest;
import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.entity.ZoneAmenity;
import dev.zbib.venueservice.exception.DuplicateZoneAmenityException;
import dev.zbib.venueservice.exception.ZoneAmenityNotFoundException;
import dev.zbib.venueservice.repository.ZoneAmenityRepository;
import dev.zbib.venueservice.repository.ZoneRepository;
import dev.zbib.venueservice.validator.ZoneAmenityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static dev.zbib.venueservice.builder.ZoneAmenityBuilder.buildZoneAmenity;

@Service
@RequiredArgsConstructor
public class ZoneAmenityService {

    private final ZoneAmenityRepository zoneAmenityRepository;
    private final ZoneRepository zoneRepository;
    private final ZoneAmenityValidator zoneAmenityValidator;
    private final AmenityService amenityService;


    public void createZoneAmenities(Zone zone, List<Amenity> amenities) {
        List<ZoneAmenity> zoneAmenities = amenities
                .stream()
                .map(amenity -> buildZoneAmenity(zone, amenity))
                .toList();
        zoneAmenityRepository.saveAll(zoneAmenities);
    }


    public void updateZoneAmenity(UUID zoneId, UpdateZoneAmenityRequest request) {
        zoneAmenityValidator.validateZoneAmenitiesUpdate(request);
        removeAmenityIds(zoneId, request.getRemovedAmenityId());
        addAmenityIds(zoneId, request.getAddedAmenityId());
    }


    private void addAmenityIds(UUID zoneId, List<UUID> amenityIds) {
        if (!amenityIds.isEmpty()) return;
        Zone zone = getZoneById(zoneId);
        if (zoneAmenityRepository.existsAnyByZoneIdAndAmenityIdIn(zone.getId(), amenityIds))
            throw new DuplicateZoneAmenityException();
        List<Amenity> amenitiesToAdd = amenityService.getAmenityByIds(amenityIds);
        createZoneAmenities(zone, amenitiesToAdd);
    }

    private void removeAmenityIds(UUID zoneId, List<UUID> amenityIds) {
        if (amenityIds.isEmpty()) return;
        if (!zoneAmenityRepository.existsAllByZoneIdAndAmenityIdIn(zoneId, amenityIds))
            throw new ZoneAmenityNotFoundException();

        zoneAmenityRepository.deleteByZoneIdAndAmenityIdIn(zoneId, amenityIds);
    }

    private Zone getZoneById(UUID zoneId) {
        return zoneRepository
                .findById(zoneId)
                .orElseThrow(null);
    }
}
