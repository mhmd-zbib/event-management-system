package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.VenueRequest;
import dev.zbib.venueservice.dto.VenueListResponse;
import dev.zbib.venueservice.dto.VenueQuery;
import dev.zbib.venueservice.dto.VenueResponse;
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
@RequestMapping("/listings")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;
    private final VenueQueryService venueQueryService;

    @PostMapping
    public ResponseEntity<String> createListing(
            @AuthenticationPrincipal Jwt jwt, @RequestBody VenueRequest req) {
        String userId = jwt.getSubject();
        venueService.createListing(userId, req);
        return ResponseEntity.ok("Listing created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getListing(@PathVariable String id) {
        return ResponseEntity.ok(venueService.getListing(id));
    }

    @GetMapping
    public ResponseEntity<Page<VenueListResponse>> getListings(
            @ModelAttribute VenueQuery filter, Pageable pageable) {
        return ResponseEntity.ok(venueQueryService.getListings(filter, pageable));
    }
}
