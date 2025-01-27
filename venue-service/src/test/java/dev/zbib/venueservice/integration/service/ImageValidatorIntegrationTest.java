package dev.zbib.venueservice.integration.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.exception.ImageMaxCountException;
import dev.zbib.venueservice.repository.ImageRepository;
import dev.zbib.venueservice.service.ImageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ImageValidatorIntegrationTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageValidator imageValidator;

    private UUID entityId;
    private List<ImageCreationRequest> imageRequests;
    private EntityType entityType;

    @BeforeEach
    void setUp() {
        // Clean up the database before each test
        imageRepository.deleteAll();

        entityId = UUID.randomUUID();
        entityType = EntityType.VENUE;
        imageRequests = new ArrayList<>();
    }

    @Test
    void validateImageCreation_Success() {
        addImageRequests(5);
        createExistingImages(3);
        assertDoesNotThrow(() -> imageValidator.validateImageCreation(entityId, imageRequests, entityType));
    }

    @Test
    void validateImageCreation_ExactlyMaxImages() {
        addImageRequests(5);
        createExistingImages(5);
        assertDoesNotThrow(() -> imageValidator.validateImageCreation(entityId, imageRequests, entityType));
    }

    @Test
    void validateImageCreation_ExceedsMaxImages() {
        addImageRequests(6);
        createExistingImages(5);
        assertThrows(ImageMaxCountException.class,
                () -> imageValidator.validateImageCreation(entityId, imageRequests, entityType));
    }

    @Test
    void validateImageCreation_EmptyList() {
        assertDoesNotThrow(() -> imageValidator.validateImageCreation(entityId, new ArrayList<>(), entityType));
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

    private void createExistingImages(int count) {
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Image image = Image
                    .builder()
                    .entityId(entityId)
                    .entityType(entityType)
                    .url("https://example.com/existing" + i + ".jpg")
                    .order(i + 1)
                    .size(1024)
                    .width(800)
                    .height(600)
                    .build();
            images.add(image);
        }
        imageRepository.saveAll(images);
    }
}