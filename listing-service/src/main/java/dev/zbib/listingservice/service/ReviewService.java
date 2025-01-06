package dev.zbib.listingservice.service;

import dev.zbib.listingservice.builder.ReviewBuilder;
import dev.zbib.listingservice.dto.CreateReviewRequest;
import dev.zbib.listingservice.dto.ReviewListResponse;
import dev.zbib.listingservice.entity.Listing;
import dev.zbib.listingservice.entity.Review;
import dev.zbib.listingservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static dev.zbib.listingservice.builder.ReviewBuilder.buildReview;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ListingService listingService;

    public void createReview(String listingId, String userId, CreateReviewRequest req) {
        Listing listing = listingService.getListingEntity(listingId);
        Review review = buildReview(userId, req, listing);
        reviewRepository.save(review);
    }

    public Page<ReviewListResponse> getReviews(String listingId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByListingId(listingId, pageable);
        return reviews.map(ReviewBuilder::buildReviewListResponse);
    }
}
