package dev.zbib.venueservice.integration.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.enums.VenueStatus;
import dev.zbib.venueservice.exception.VenueAlreadyExistException;
import dev.zbib.venueservice.exception.VenueMaxCapacityException;
import dev.zbib.venueservice.repository.ImageRepository;
import dev.zbib.venueservice.repository.VenueRepository;
import dev.zbib.venueservice.service.VenueService;
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
class VenueServiceIntegrationTest {

    @Autowired
    private VenueService venueService;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private ImageRepository imageRepository;

    private UUID userId;
    private VenueCreationRequest request;
    private List<ImageCreationRequest> imageRequests;

    @BeforeEach
    void setUp() {
        imageRepository.deleteAll();
        venueRepository.deleteAll();

        userId = UUID.randomUUID();
        imageRequests = new ArrayList<>();

        ImageCreationRequest imageRequest = new ImageCreationRequest();
        imageRequest.setUrl("https://example.com/image.jpg");
        imageRequest.setOrder(1);
        imageRequest.setSize(1024);
        imageRequest.setWidth(800);
        imageRequest.setHeight(600);
        imageRequests.add(imageRequest);

        request = VenueCreationRequest
                .builder()
                .name("Test Venue")
                .description("Test Description")
                .maxCapacity(50)
                .bookingTimeline(30)
                .images(imageRequests)
                .build();
    }

    @Test
    void createVenue_Success() {
        VenueCreationResponse response = venueService.createVenue(userId, request);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getMaxCapacity(), response.getTotalCapacity());
        assertEquals(request.getBookingTimeline(), response.getBookingTimeline());
        assertEquals(VenueStatus.PENDING.getDisplayName(), response.getStatus());

        Venue savedVenue = venueRepository
                .findById(response.getId())
                .orElse(null);
        assertNotNull(savedVenue);
        assertEquals(request.getName(), savedVenue.getName());
        assertEquals(request.getMaxCapacity(), savedVenue.getMaxCapacity());
        assertEquals(userId, savedVenue.getOwnerId());

        List<Image> savedImages = imageRepository.findByEntityIdAndEntityType(response.getId(), EntityType.VENUE);
        assertEquals(imageRequests.size(), savedImages.size());

        Image savedImage = savedImages.get(0);
        ImageCreationRequest imageRequest = imageRequests.get(0);
        assertEquals(imageRequest.getUrl(), savedImage.getUrl());
        assertEquals(imageRequest.getOrder(), savedImage.getOrder());
        assertEquals(imageRequest.getSize(), savedImage.getSize());
        assertEquals(imageRequest.getWidth(), savedImage.getWidth());
        assertEquals(imageRequest.getHeight(), savedImage.getHeight());
    }

    @Test
    void createVenue_DuplicateName() {
        venueService.createVenue(userId, request);
        assertThrows(VenueAlreadyExistException.class, () -> venueService.createVenue(userId, request));
    }

    @Test
    void createVenue_InvalidCapacity() {
        request.setMaxCapacity(5);
        assertThrows(VenueMaxCapacityException.class, () -> venueService.createVenue(userId, request));
    }

    @Test
    void createVenue_NoImages() {
        request.setImages(new ArrayList<>());

        VenueCreationResponse response = venueService.createVenue(userId, request);
        assertNotNull(response);
        List<Image> savedImages = imageRepository.findByEntityIdAndEntityType(response.getId(), EntityType.VENUE);
        assertTrue(savedImages.isEmpty());
    }
}