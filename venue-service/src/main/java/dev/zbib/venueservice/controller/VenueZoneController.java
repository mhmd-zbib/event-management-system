package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/venues/{id}/zones")
@RequiredArgsConstructor
public class VenueZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ZoneCreationResponse> createZone(@PathVariable UUID id,
            @RequestBody ZoneCreationRequest zoneCreationRequest) {
        ZoneCreationResponse res = zoneService.createZone(id, zoneCreationRequest);
        return ResponseEntity.ok(res);
    }
}
