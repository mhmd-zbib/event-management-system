package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.VenueListResponse;
import dev.zbib.venueservice.service.VenueQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userId}/listings")
@RequiredArgsConstructor
public class UserVenueController {

    private final VenueQueryService listingService;

    @GetMapping
    public ResponseEntity<Page<VenueListResponse>> getUserListings(
            @PathVariable String userId, Pageable pageable) {
        return ResponseEntity.ok(listingService.getListingsByUserId(userId, pageable));
    }
}
