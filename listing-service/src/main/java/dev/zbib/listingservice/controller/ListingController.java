package dev.zbib.listingservice.controller;

import dev.zbib.listingservice.dto.CreateListingRequest;
import dev.zbib.listingservice.dto.ListingListResponse;
import dev.zbib.listingservice.dto.ListingQuery;
import dev.zbib.listingservice.dto.ListingResponse;
import dev.zbib.listingservice.service.ListingQueryService;
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

    //  TODO: search
    //  TODO: analytics
    //  TODO: review management
    //  TODO: interest notification

    private final ListingService listingService;
    private final ListingQueryService listingQueryService;

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
    public ResponseEntity<Page<ListingListResponse>> getListings(
            @ModelAttribute ListingQuery filter, Pageable pageable) {
        return ResponseEntity.ok(listingQueryService.getListings(filter, pageable));
    }
}
