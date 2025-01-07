package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.VenueListResponse;
import dev.zbib.venueservice.dto.VenueQuery;
import dev.zbib.venueservice.dto.VenueRequest;
import dev.zbib.venueservice.dto.VenueResponse;
import dev.zbib.venueservice.service.ReviewService;
import dev.zbib.venueservice.service.VenueQueryService;
import dev.zbib.venueservice.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;
    private final VenueQueryService venueQueryService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> createVenue(
            @AuthenticationPrincipal Jwt jwt, @RequestBody VenueRequest req) {
        String userId = jwt.getSubject();
        venueService.createVenue(userId, req);
        return ResponseEntity.ok("Listing created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getVenue(@PathVariable String id) {
        return ResponseEntity.ok(venueService.getVenue(id));
    }

    @GetMapping
    public ResponseEntity<Page<VenueListResponse>> getVenues(
            @ModelAttribute VenueQuery filter, Pageable pageable) {
        return ResponseEntity.ok(venueQueryService.getVenues(filter, pageable));
    }

    @GetMapping("/{id}/booking-check")
    public ResponseEntity<String> checkVenueAvailability(@PathVariable String id) {
        venueService.checkVenueAvailability(id);
        return ResponseEntity.ok("Venue validated");
    }
}
