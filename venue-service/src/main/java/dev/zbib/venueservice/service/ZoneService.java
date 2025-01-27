package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.repository.ZoneRepository;
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

    public ZoneCreationResponse createZone(UUID venueId, ZoneCreationRequest request) {
        Venue venue = venueService.getVenueById(venueId);
        Zone zone = buildZone(request, venue);
        Zone savedZone = zoneRepository.save(zone);
        return buildZoneCreationResponse(savedZone);
    }
}
