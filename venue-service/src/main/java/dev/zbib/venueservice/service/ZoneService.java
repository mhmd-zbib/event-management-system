package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneCreationResponse createZone(ZoneCreationRequest request) {
        Zone zone = Zone
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        Zone savedZone = zoneRepository.save(zone);
        return ZoneCreationResponse
                .builder()
                .id(savedZone.getId())
                .name(savedZone.getName())
                .description(savedZone.getDescription())
                .build();
    }
}
