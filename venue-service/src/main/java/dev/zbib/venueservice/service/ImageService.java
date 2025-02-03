package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.repository.ImageRepository;
import dev.zbib.venueservice.validator.ImageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static dev.zbib.venueservice.builder.ImageBuilder.buildImage;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageValidator imageValidator;

    public void createImages(UUID entityId, List<ImageCreationRequest> requests, EntityType entityType) {
        imageValidator.validateImageCreation(entityId, requests, entityType);
        List<Image> images = requests
                .stream()
                .map(request -> buildImage(entityId, request, entityType))
                .toList();
        imageRepository.saveAll(images);
    }
}

