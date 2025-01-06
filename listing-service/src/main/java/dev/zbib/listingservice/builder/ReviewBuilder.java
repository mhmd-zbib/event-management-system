package dev.zbib.listingservice.builder;

import dev.zbib.listingservice.dto.CreateReviewRequest;
import dev.zbib.listingservice.dto.ReviewListResponse;
import dev.zbib.listingservice.entity.Listing;
import dev.zbib.listingservice.entity.Review;

public class ReviewBuilder {

    public static Review buildReview(String userId, CreateReviewRequest req, Listing listing) {
        return Review.builder()
                .userId(userId)
                .comment(req.getComment())
                .listing(listing)
                .rating(0)
                .build();
    }

    public static ReviewListResponse buildReviewListResponse(Review review) {
        return ReviewListResponse.builder()
                .id(review.getId())
                .comment(review.getComment())
                .userId(review.getUserId())
                .rating(review.getRating())
                .build();
    }
}
