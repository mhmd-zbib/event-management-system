package dev.zbib.listingservice.dto;

import lombok.Data;

@Data
public class CreateListingRequest {

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private String category;

}
