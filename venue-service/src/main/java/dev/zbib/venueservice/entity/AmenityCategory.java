package dev.zbib.venueservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "amenity_categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmenityCategory {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    private String icon;

    private int sortOrder;

}
