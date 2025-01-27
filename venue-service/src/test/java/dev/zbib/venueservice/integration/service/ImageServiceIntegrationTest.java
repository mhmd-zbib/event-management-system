package dev.zbib.venueservice.integration.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.exception.ImageMaxCountException;
import dev.zbib.venueservice.repository.ImageRepository;
import dev.zbib.venueservice.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ImageServiceIntegrationTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    private UUID entityId;
    private List<ImageCreationRequest> imageRequests;
    private EntityType entityType;

    @BeforeEach
    void setUp() {
        imageRepository.deleteAll();

        entityId = UUID.randomUUID();
        entityType = EntityType.VENUE;
        imageRequests = new ArrayList<>();
    }

    @Test
    void createImages_Success() {
        addImageRequests(3);

        assertDoesNotThrow(() -> imageService.createImages(entityId, imageRequests, entityType));

        List<Image> savedImages = imageRepository.findAllByEntityIdAndEntityType(entityId, entityType);
        assertEquals(imageRequests.size(), savedImages.size());

        for (int i = 0; i < savedImages.size(); i++) {
            Image savedImage = savedImages.get(i);
            ImageCreationRequest request = imageRequests.get(i);

            assertEquals(entityId, savedImage.getEntityId());
            assertEquals(entityType, savedImage.getEntityType());
            assertEquals(request.getUrl(), savedImage.getUrl());
            assertEquals(request.getOrder(), savedImage.getOrder());
            assertEquals(request.getSize(), savedImage.getSize());
            assertEquals(request.getWidth(), savedImage.getWidth());
            assertEquals(request.getHeight(), savedImage.getHeight());
        }
    }

    @Test
    void createImages_ExceedMaxLimit() {
        addImageRequests(11);

        assertThrows(ImageMaxCountException.class,
                () -> imageService.createImages(entityId, imageRequests, entityType));

        List<Image> savedImages = imageRepository.findAllByEntityIdAndEntityType(entityId, entityType);
        assertTrue(savedImages.isEmpty());
    }

    @Test
    void createImages_EmptyList() {
        imageService.createImages(entityId, new ArrayList<>(), entityType);

        List<Image> savedImages = imageRepository.findAllByEntityIdAndEntityType(entityId, entityType);
        assertTrue(savedImages.isEmpty());
    }

    @Test
    void createImages_MultipleEntities() {
        UUID entityId2 = UUID.randomUUID();
        addImageRequests(3);

        imageService.createImages(entityId, imageRequests, entityType);
        imageService.createImages(entityId2, imageRequests, entityType);

        List<Image> savedImagesEntity1 = imageRepository.findAllByEntityIdAndEntityType(entityId, entityType);
        List<Image> savedImagesEntity2 = imageRepository.findAllByEntityIdAndEntityType(entityId2, entityType);

        assertEquals(imageRequests.size(), savedImagesEntity1.size());
        assertEquals(imageRequests.size(), savedImagesEntity2.size());
    }

    private void addImageRequests(int count) {
        for (int i = 0; i < count; i++) {
            ImageCreationRequest request = new ImageCreationRequest();
            request.setUrl("https://example.com/image" + i + ".jpg");
            request.setOrder(i + 1);
            request.setSize(1024);
            request.setWidth(800);
            request.setHeight(600);
            imageRequests.add(request);
        }
    }
}