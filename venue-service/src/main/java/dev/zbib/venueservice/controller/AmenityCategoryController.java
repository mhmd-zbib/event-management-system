package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest;
import dev.zbib.venueservice.service.AmenityCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amenity-categories")
@RequiredArgsConstructor
public class AmenityCategoryController {

    private final AmenityCategoryService amenityCategoryService;

    @PostMapping
    public ResponseEntity<String> createAmenityCategory(
            @RequestBody
            AmenityCategoryCreationRequest request) {
        amenityCategoryService.createAmenityCategory(request);
        return ResponseEntity.ok("Success");
    }
}
