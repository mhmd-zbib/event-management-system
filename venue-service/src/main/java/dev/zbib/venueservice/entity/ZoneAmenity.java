package dev.zbib.venueservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "zone_amenities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneAmenity {

    @Id
    @GeneratedValue
    private UUID id;

    private String note;

    @OneToOne
    private Amenity amenity;

    @OneToOne
    private Zone zone;

}
