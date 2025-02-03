package dev.zbib.venueservice.unit.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.exception.ImageMaxCountException;
import dev.zbib.venueservice.repository.ImageRepository;
import dev.zbib.venueservice.service.ImageService;
import dev.zbib.venueservice.validator.ImageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceUnitTest {

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ImageValidator imageValidator;

    @InjectMocks
    private ImageService imageService;

    private UUID entityId;
    private List<ImageCreationRequest> imageRequests;
    private EntityType entityType;

    @BeforeEach
    void setUp() {
        entityId = UUID.randomUUID();
        entityType = EntityType.VENUE;

        imageRequests = new ArrayList<>();
        ImageCreationRequest request1 = new ImageCreationRequest();
        request1.setUrl("https://example.com/image1.jpg");
        request1.setOrder(1);
        request1.setSize(1024);
        request1.setWidth(800);
        request1.setHeight(600);

        ImageCreationRequest request2 = new ImageCreationRequest();
        request2.setUrl("https://example.com/image2.jpg");
        request2.setOrder(2);
        request2.setSize(2048);
        request2.setWidth(1024);
        request2.setHeight(768);

        imageRequests.add(request1);
        imageRequests.add(request2);
    }

    @Test
    void createImages_Success() {
        doNothing()
                .when(imageValidator)
                .validateImageCreation(entityId, imageRequests, entityType);
        when(imageRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> imageService.createImages(entityId, imageRequests, entityType));

        verify(imageValidator).validateImageCreation(entityId, imageRequests, entityType);
        verify(imageRepository).saveAll(anyList());
    }

    @Test
    void createImages_ValidationFailure() {
        doThrow(new ImageMaxCountException())
                .when(imageValidator)
                .validateImageCreation(entityId, imageRequests, entityType);
        assertThrows(ImageMaxCountException.class,
                () -> imageService.createImages(entityId, imageRequests, entityType));


        verify(imageValidator).validateImageCreation(entityId, imageRequests, entityType);
        verify(imageRepository, never()).saveAll(any());
    }

    @Test
    void createImages_EmptyList() {
        List<ImageCreationRequest> emptyList = new ArrayList<>();

        imageService.createImages(entityId, emptyList, entityType);

        verify(imageValidator).validateImageCreation(entityId, emptyList, entityType);
        verify(imageRepository).saveAll(anyList());
    }

    @Test
    void createImages_VerifyImageBuilding() {
        doNothing()
                .when(imageValidator)
                .validateImageCreation(entityId, imageRequests, entityType);
        when(imageRepository.saveAll(anyList())).thenAnswer(invocation -> {
            List<Image> savedImages = invocation.getArgument(0);
            assertEquals(imageRequests.size(), savedImages.size());
            for (int i = 0; i < savedImages.size(); i++) {
                Image image = savedImages.get(i);
                ImageCreationRequest request = imageRequests.get(i);
                assertEquals(entityId, image.getEntityId());
                assertEquals(entityType, image.getEntityType());
                assertEquals(request.getUrl(), image.getUrl());
                assertEquals(request.getOrder(), image.getOrder());
                assertEquals(request.getSize(), image.getSize());
                assertEquals(request.getWidth(), image.getWidth());
                assertEquals(request.getHeight(), image.getHeight());
            }
            return savedImages;
        });

        imageService.createImages(entityId, imageRequests, entityType);

        verify(imageValidator).validateImageCreation(entityId, imageRequests, entityType);
        verify(imageRepository).saveAll(anyList());
    }
}