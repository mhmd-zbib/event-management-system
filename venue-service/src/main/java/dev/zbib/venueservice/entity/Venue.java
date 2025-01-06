package dev.zbib.venueservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "venues")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venue {

    @Id
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

    private List<String> images;

    private List<String> tags;

    private boolean isFeatured;
}
