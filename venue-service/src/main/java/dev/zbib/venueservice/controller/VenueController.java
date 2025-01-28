package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    @Operation(summary = "Create a new venue",
               description = "Creates a new venue with the provided details. Requires authentication.")
    public ResponseEntity<VenueCreationResponse> createVenue(@Parameter(hidden = true) @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "Venue creation details", required = true,
                       schema = @Schema(implementation = VenueCreationRequest.class)) @RequestBody
            VenueCreationRequest venueCreationRequest) {
        UUID userId = UUID.fromString(jwt.getSubject());
        VenueCreationResponse res = venueService.createVenue(userId, venueCreationRequest);
        return ResponseEntity.ok(res);
    }
}
