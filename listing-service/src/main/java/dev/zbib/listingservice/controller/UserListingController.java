package dev.zbib.listingservice.controller;

import dev.zbib.listingservice.dto.ListingListResponse;
import dev.zbib.listingservice.service.ListingService;
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
public class UserListingController {

    private final ListingService listingService;

    @GetMapping
    public ResponseEntity<Page<ListingListResponse>> getUserListings(
            @PathVariable String userId, Pageable pageable) {
        return ResponseEntity.ok(listingService.getListingsByUserId(userId, pageable));
    }
}
