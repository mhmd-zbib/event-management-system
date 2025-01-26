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

    @NotNull(message = "Entity ID is required")
    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @NotNull(message = "Entity type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

    @NotBlank(message = "URL is required")
    @Pattern(regexp = "^(https?)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL format")
    @Column(nullable = false)
    private String url;

    @Min(value = 0, message = "Order must be non-negative")
    @Column(name = "order", nullable = false)
    private int order;

    @Positive(message = "Size must be positive")
    @Max(value = 5242880, message = "File size cannot exceed 5MB")
    @Column(nullable = false)
    private int size;

    @Min(value = 100, message = "Width must be at least 100 pixels")
    @Max(value = 4096, message = "Width cannot exceed 4096 pixels")
    @Column(nullable = false)
    private int width;

    @Min(value = 100, message = "Height must be at least 100 pixels")
    @Max(value = 4096, message = "Height cannot exceed 4096 pixels")
    @Column(nullable = false)
    private int height;

}