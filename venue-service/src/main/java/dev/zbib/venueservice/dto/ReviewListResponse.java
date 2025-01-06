package dev.zbib.venueservice.dto;

import dev.zbib.venueservice.entity.Venue;
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

    private Venue venueId;

}
