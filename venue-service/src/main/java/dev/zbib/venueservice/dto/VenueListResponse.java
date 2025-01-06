package dev.zbib.venueservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueListResponse {

    private String id;
    private String name;
    private String type;
    private int capacity;
    private Double price;
    private boolean isFeatured;

}
