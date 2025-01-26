package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.service.VenueService;
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
    public ResponseEntity<VenueCreationResponse> createVenue(@AuthenticationPrincipal Jwt jwt,
            @RequestBody VenueCreationRequest venueCreationRequest) {
        UUID userId = UUID.fromString(jwt.getSubject());
        VenueCreationResponse res = venueService.createVenue(userId, venueCreationRequest);
        return ResponseEntity.ok(res);
    }
}
