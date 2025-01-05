package dev.zbib.listingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateListingRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String category;

}
