package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ImageRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static dev.zbib.venueservice.builder.ImageBuilder.buildImage;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImages(UUID entityId, List<ImageRequest> requests, EntityType entityType) {
        List<Image> images = requests
                .stream()
                .map(request -> buildImage(entityId, request, entityType))
                .toList();
        imageRepository.saveAll(images);
    }
}

