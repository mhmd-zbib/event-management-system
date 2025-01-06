package dev.zbib.listingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingQuery {

    private Double minPrice;

    private Double maxPrice;

    private String category;

    private String name;

    private String description;

}
