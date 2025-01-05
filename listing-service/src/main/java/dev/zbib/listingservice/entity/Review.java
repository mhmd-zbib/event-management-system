package dev.zbib.listingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Review {

    @Id
    private UUID id;

    private UUID userId;

    private String comment;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

}
