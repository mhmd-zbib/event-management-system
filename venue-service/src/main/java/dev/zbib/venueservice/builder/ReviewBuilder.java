package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.ReviewRequest;
import dev.zbib.venueservice.dto.ReviewListResponse;
import dev.zbib.venueservice.entity.Review;

public class ReviewBuilder {

    public static Review buildReview(String userId, String venueId, ReviewRequest req) {
        return Review.builder()
                .userId(userId)
                .comment(req.getComment())
                .venueId(venueId)
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
