package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.service.ZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/venues/{id}/zones")
@RequiredArgsConstructor
@Tag(name = "Venue Zones", description = "Operations for managing zones within venues")
public class VenueZoneController {

    private final ZoneService zoneService;

    @PostMapping
    @Operation(summary = "Create Zone",
            description = "Creates a new zone within a specified venue with given specifications and images")
    public ResponseEntity<ZoneCreationResponse> createZone(
            @Parameter(description = "ID of the venue",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable
            UUID id,
            @Parameter(description = "Zone details including dimensions, capacity, and images", required = true)
            @Valid
            @RequestBody
            ZoneCreationRequest zoneCreationRequest) {
        ZoneCreationResponse response = zoneService.createZone(id, zoneCreationRequest);
        return ResponseEntity.ok(response);
    }
}