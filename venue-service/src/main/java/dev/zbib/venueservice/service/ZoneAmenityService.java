package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.UpdateZoneAmenityRequest;
import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.entity.ZoneAmenity;
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
        //  TODO: Throw 404
        zoneAmenityValidator.validateZoneAmenitiesUpdate(request);
        removeAmenityIds(zoneId, request.getRemovedAmenityId());
        addAmenityIds(zoneId, request.getAddedAmenityId());
    }


    private void addAmenityIds(UUID zoneId, List<UUID> amenityIds) {
        if (!amenityIds.isEmpty()) return;
        Zone zone = getZoneById(zoneId);
        if (zoneAmenityRepository.existsAnyByZoneIdAndAmenityIdIn(zone.getId(), amenityIds))
            throw new IllegalArgumentException("Some amenities already exist in this zone"); // TODO: Create error
        List<Amenity> amenitiesToAdd = amenityService.getAmenityByIds(amenityIds);
        createZoneAmenities(zone, amenitiesToAdd);
    }

    private void removeAmenityIds(UUID zoneId, List<UUID> amenityIds) {
        if (amenityIds.isEmpty()) return;
        if (!zoneAmenityRepository.existsAllByZoneIdAndAmenityIdIn(zoneId, amenityIds))
            throw new IllegalArgumentException("Some amenities to remove don't exist in this zone");  // TODO: Create error

        zoneAmenityRepository.deleteByZoneIdAndAmenityIdIn(zoneId, amenityIds);
    }

    private Zone getZoneById(UUID zoneId) {
        return zoneRepository
                .findById(zoneId)
                .orElseThrow(null);
    }
}
