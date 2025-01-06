package dev.zbib.venueservice.dto;

import dev.zbib.venueservice.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueResponse {

    private String id;

    private String name;

    private String description;

    private String type;

    private int capacity;

    private String category;

    private double rating;

    private Double price;

    private String ownerId;

    private boolean available;

    private Location location;

    private List<String> tags;

    private boolean isFeatured;

}
