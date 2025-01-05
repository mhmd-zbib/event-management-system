package dev.zbib.listingservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "reviews")
public class Review {

    @Id
    private UUID id;

    private UUID userId;

    private String comment;

    private Integer rating;

    private Listing listing;

}
