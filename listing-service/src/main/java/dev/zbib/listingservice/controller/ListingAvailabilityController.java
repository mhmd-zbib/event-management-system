package dev.zbib.listingservice.controller;

import dev.zbib.listingservice.service.ListingAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listings/{id}")
@RequiredArgsConstructor
public class ListingAvailabilityController {

    private final ListingAvailabilityService listingService;

    @GetMapping("/available")
    public ResponseEntity<Boolean> getAvailability(@PathVariable String id) {
        return ResponseEntity.ok(listingService.getAvailability(id));
    }

    @GetMapping("/available")
    public ResponseEntity<String> setAvailable(@PathVariable String id) {
        listingService.setAvailable(id);
        return ResponseEntity.ok("Set");
    }

    @GetMapping("/unavailable")
    public ResponseEntity<String> setUnavailable(@PathVariable String id) {
        listingService.setUnavailable(id);
        return ResponseEntity.ok("Set");
    }
}
