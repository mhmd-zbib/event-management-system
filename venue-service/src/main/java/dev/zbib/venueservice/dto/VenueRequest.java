package dev.zbib.venueservice.dto;

import dev.zbib.venueservice.entity.Location;
import lombok.Data;

import java.util.List;

@Data
public class VenueRequest {

    private String name;

    private String description;

    private String type;

    private Double price;

    private int capacity;

    private String category;

    private Location location;

    private List<String> images;

    private List<String> tags;

}
