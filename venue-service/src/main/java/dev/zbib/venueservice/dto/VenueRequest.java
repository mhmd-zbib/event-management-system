package dev.zbib.venueservice.dto;

import lombok.Data;

@Data
public class VenueRequest {

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private String category;

}
