package dev.zbib.listingservice.controller;

import dev.zbib.listingservice.dto.CreateListingRequest;
import dev.zbib.listingservice.dto.ListingResponse;
import dev.zbib.listingservice.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;    

    @PostMapping
    public ResponseEntity<String> createListing(
            @AuthenticationPrincipal Jwt jwt,
            CreateListingRequest req) {
        String userId = jwt.getSubject();
        listingService.createListing(userId, req);
        return ResponseEntity.ok("Listing created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingResponse> getListing(@PathVariable UUID id) {
        return ResponseEntity.ok(listingService.getListing(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListingResponse>> getListings(Pageable pageable) {
        return ResponseEntity.ok(listingService.getListings(pageable));
    }

    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> getAvailability(@PathVariable UUID id){
        return ResponseEntity.ok(listingService.getAvailability(id));
    }



}
