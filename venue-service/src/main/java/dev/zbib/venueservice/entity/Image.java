package dev.zbib.venueservice.entity;

import dev.zbib.venueservice.enums.EntityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(nullable = false)
    private String url;

    @Column(name = "order", nullable = false)
    private int order;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;
}