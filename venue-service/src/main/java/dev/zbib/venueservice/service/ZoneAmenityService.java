package dev.zbib.venueservice.service;

import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.entity.ZoneAmenity;
import dev.zbib.venueservice.repository.ZoneAmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.venueservice.builder.ZoneAmenityBuilder.buildZoneAmenity;

@Service
@RequiredArgsConstructor
public class ZoneAmenityService {

    private final ZoneAmenityRepository zoneAmenityRepository;



    public void createZoneAmenities(Zone zone, List<Amenity> amenities) {
        List<ZoneAmenity> zoneAmenities = amenities
                .stream()
                .map(amenity -> buildZoneAmenity(zone, amenity))
                .toList();
        zoneAmenityRepository.saveAll(zoneAmenities);
    }
}
