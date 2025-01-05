package dev.zbib.listingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ListingFilter {

    private Double minPrice;

    private Double maxPrice;

    private String category;

}
