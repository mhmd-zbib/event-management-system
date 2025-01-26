package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    long countByEntityIdAndEntityType(UUID entityId, List<ImageCreationRequest> requests, EntityType entityType);

    List<Image> findByEntityIdAndEntityType(UUID id, EntityType entityType);

    List<Image> findAllByEntityIdAndEntityType(UUID entityId, EntityType entityType);
}


