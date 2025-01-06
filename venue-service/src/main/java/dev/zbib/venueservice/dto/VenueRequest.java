package dev.zbib.venueservice.dto;

import dev.zbib.venueservice.entity.Availability;
import dev.zbib.venueservice.entity.Location;
import lombok.Data;

import java.util.List;

@Data
public class VenueRequest {

    private String name;

    private String description;

    private String type;

    private int capacity;

    private String category;

    private List<Availability> availabilities;

    private Location location;

    private List<String> images;

    private List<String> tags;

}
