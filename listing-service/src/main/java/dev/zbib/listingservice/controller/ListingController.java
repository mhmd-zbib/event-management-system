package dev.zbib.listingservice.controller;

import dev.zbib.listingservice.dto.CreateListingRequest;
import dev.zbib.listingservice.dto.ListingFilter;
import dev.zbib.listingservice.dto.ListingListResponse;
import dev.zbib.listingservice.dto.ListingResponse;
import dev.zbib.listingservice.service.ListingService;
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
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<String> createListing(
            @AuthenticationPrincipal Jwt jwt, @RequestBody CreateListingRequest req) {
        String userId = jwt.getSubject();
        listingService.createListing(userId, req);
        return ResponseEntity.ok("Listing created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingResponse> getListing(@PathVariable String id) {
        return ResponseEntity.ok(listingService.getListing(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListingListResponse>> getListings( @ModelAttribute ListingFilter filter, Pageable pageable) {
        return ResponseEntity.ok(listingService.getListings(filter, pageable));
    }

    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> getAvailability(@PathVariable String id) {
        return ResponseEntity.ok(listingService.getAvailability(id));
    }
}
