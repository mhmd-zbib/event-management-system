package dev.zbib.listingservice.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateListingRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String category;

}
