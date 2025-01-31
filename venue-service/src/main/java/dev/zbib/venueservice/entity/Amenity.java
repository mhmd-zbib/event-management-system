package dev.zbib.venueservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "amenities")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amenity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private AmenityCategory category;

    private String name;

    private String description;

    private String iconUrl;

}
