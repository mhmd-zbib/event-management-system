package dev.zbib.venueservice.entity;

import dev.zbib.venueservice.enums.EntityType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    private UUID id;

    private UUID entityId;

    private EntityType entityType;

    private String url;

    private int sort_order;

    private int size;

    private int width;

    private int height;

}
