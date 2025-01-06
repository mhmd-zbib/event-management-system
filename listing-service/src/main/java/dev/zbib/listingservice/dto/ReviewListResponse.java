package dev.zbib.listingservice.dto;

import dev.zbib.listingservice.entity.Listing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListResponse {

    private String id;

    private String userId;

    private String comment;

    private Integer rating;

    private Listing listingId;

}
