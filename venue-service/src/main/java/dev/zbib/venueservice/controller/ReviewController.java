package dev.zbib.venueservice.controller;

import dev.zbib.venueservice.dto.ReviewRequest;
import dev.zbib.venueservice.dto.ReviewListResponse;
import dev.zbib.venueservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listings/{listingId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> createReview(
            @PathVariable String listingId, @AuthenticationPrincipal Jwt jwt, @RequestBody ReviewRequest req) {
        String userId = jwt.getSubject();
        reviewService.createReview(listingId, userId, req);
        return ResponseEntity.ok("Review added");
    }

    @GetMapping
    public ResponseEntity<Page<ReviewListResponse>> getReviews(@PathVariable String listingId, Pageable pageable) {
        return ResponseEntity.ok(reviewService.getReviews(listingId, pageable));
    }
}
