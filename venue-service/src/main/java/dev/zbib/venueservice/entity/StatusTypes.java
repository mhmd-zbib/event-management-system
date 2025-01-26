package dev.zbib.venueservice.entity;

import dev.zbib.venueservice.enums.EntityType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class StatusTypes {

    @Id
    @GeneratedValue
    private UUID id;

    private EntityType type;

    private String name;

    private String displayName;

    private int order;

}
