package dev.zbib.venueservice.service;

import dev.zbib.venueservice.builder.ReviewBuilder;
import dev.zbib.venueservice.dto.ReviewRequest;
import dev.zbib.venueservice.dto.ReviewListResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.entity.Review;
import dev.zbib.venueservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static dev.zbib.venueservice.builder.ReviewBuilder.buildReview;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final VenueService venueService;

    public void createReview(String listingId, String userId, ReviewRequest req) {
        Venue venue = venueService.getListingEntity(listingId);
        Review review = buildReview(userId, req, venue);
        reviewRepository.save(review);
    }

    public Page<ReviewListResponse> getReviews(String listingId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByListingId(listingId, pageable);
        return reviews.map(ReviewBuilder::buildReviewListResponse);
    }
}
