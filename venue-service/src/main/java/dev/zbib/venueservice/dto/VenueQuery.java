package dev.zbib.venueservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueQuery {

    private Double minPrice;

    private Double maxPrice;

    private String category;

    private String name;

    private String description;

}
