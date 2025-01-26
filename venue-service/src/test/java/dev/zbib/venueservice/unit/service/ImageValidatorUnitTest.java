package dev.zbib.venueservice.unit.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.exception.MaxImageCountException;
import dev.zbib.venueservice.repository.ImageRepository;
import dev.zbib.venueservice.service.ImageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageValidatorUnitTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageValidator imageValidator;

    private UUID entityId;
    private List<ImageCreationRequest> imageRequests;
    private EntityType entityType;

    @BeforeEach
    void setUp() {
        entityId = UUID.randomUUID();
        entityType = EntityType.VENUE;
        imageRequests = new ArrayList<>();
    }

    @Test
    void validateImageCreation_Success() {
        addImageRequests(5);
        when(imageRepository.countByEntityIdAndEntityType(any(),
                anyList(),
                any())).thenReturn(3L);
        assertDoesNotThrow(() -> imageValidator.validateImageCreation(entityId, imageRequests, entityType));
    }

    @Test
    void validateImageCreation_ExactlyMaxImages() {
        addImageRequests(5);
        when(imageRepository.countByEntityIdAndEntityType(any(),
                anyList(),
                any())).thenReturn(5L);

        assertDoesNotThrow(() -> imageValidator.validateImageCreation(entityId, imageRequests, entityType));
    }

    @Test
    void validateImageCreation_ExceedsMaxImages() {
        addImageRequests(6);
        when(imageRepository.countByEntityIdAndEntityType(any(),
                anyList(),
                any())).thenReturn(5L);
        assertThrows(MaxImageCountException.class,
                () -> imageValidator.validateImageCreation(entityId, imageRequests, entityType));
    }

    @Test
    void validateImageCreation_EmptyList() {
        when(imageRepository.countByEntityIdAndEntityType(any(), anyList(), any())).thenReturn(0L);
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
}