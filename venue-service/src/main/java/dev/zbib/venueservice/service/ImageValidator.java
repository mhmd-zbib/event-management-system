package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.exception.ImageMaxCountException;
import dev.zbib.venueservice.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageValidator {

    private static final long MAX_IMAGES_COUNT = 10;

    private final ImageRepository imageRepository;

    public void validateImageCreation(UUID entityId, List<ImageCreationRequest> requests, EntityType entityType) {
        validateImagesCount(entityId, requests, entityType);
    }

    private void validateImagesCount(UUID entityId, List<ImageCreationRequest> requests, EntityType entityType) {
        long existingImagesCount = imageRepository.countByEntityIdAndEntityType(entityId, requests, entityType);
        long newImagesCount = requests.size();
        if (existingImagesCount + newImagesCount > MAX_IMAGES_COUNT) {
            throw new ImageMaxCountException();
        }
    }
}
