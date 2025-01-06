package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueQuery {

    private Double minPrice;
    private Double maxPrice;
    private String category;
    private Integer minCapacity;
    private Integer maxCapacity;
    private Double minRating;
    private Double maxRating;
    private Boolean available;
    private Boolean isFeatured;

}
