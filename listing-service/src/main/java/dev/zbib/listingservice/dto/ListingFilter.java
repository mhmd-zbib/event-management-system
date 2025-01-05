package dev.zbib.listingservice.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ListingFilter {

    private UUID userId;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String category;

}
