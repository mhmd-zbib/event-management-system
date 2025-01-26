package dev.zbib.venueservice.unit.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.entity.StatusTypes;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.enums.VenueStatus;
import dev.zbib.venueservice.exception.VenueAlreadyExistException;
import dev.zbib.venueservice.exception.VenueMaxCapacityException;
import dev.zbib.venueservice.repository.VenueRepository;
import dev.zbib.venueservice.service.ImageService;
import dev.zbib.venueservice.service.VenueService;
import dev.zbib.venueservice.service.VenueValidator;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @Mock
    private VenueValidator venueValidator;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private VenueService venueService;

    private UUID userId;
    private VenueCreationRequest request;
    private Venue venue;
    private List<ImageCreationRequest> imageRequests;
    private StatusTypes statusType;
    private VenueCreationResponse expectedResponse;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        UUID venueId = UUID.randomUUID();

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

        statusType = new StatusTypes();
        statusType.setId(UUID.randomUUID());
        statusType.setType(EntityType.VENUE);
        statusType.setName(VenueStatus.PENDING.name());
        statusType.setDisplayName(VenueStatus.PENDING.getDisplayName());

        venue = Venue
                .builder()
                .id(venueId)
                .ownerId(userId)
                .name("Test Venue")
                .description("Test Description")
                .maxCapacity(50)
                .bookingTimeline(30)
                .status(statusType)
                .build();

        expectedResponse = VenueCreationResponse
                .builder()
                .id(venueId)
                .ownerId(userId)
                .name("Test Venue")
                .status(VenueStatus.PENDING.getDisplayName())
                .totalCapacity(50)
                .bookingTimeline(30)
                .build();
    }

    @Test
    void createVenue_Success() {
        when(venueRepository.save(any(Venue.class))).thenReturn(venue);
        doNothing()
                .when(venueValidator)
                .validateVenueCreation(request);
        doNothing()
                .when(imageService)
                .createImages(any(UUID.class), anyList(), any(EntityType.class));

        VenueCreationResponse actualResponse = venueService.createVenue(userId, request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getOwnerId(), actualResponse.getOwnerId());
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getTotalCapacity(), actualResponse.getTotalCapacity());
        assertEquals(expectedResponse.getBookingTimeline(), actualResponse.getBookingTimeline());

        verify(venueValidator).validateVenueCreation(request);
        verify(venueRepository).save(any(Venue.class));
        verify(imageService).createImages(venue.getId(), request.getImages(), EntityType.VENUE);
    }

    @Test
    void createVenue_ThrowsVenueAlreadyExistException() {
        doThrow(new VenueAlreadyExistException())
                .when(venueValidator)
                .validateVenueCreation(request);
        assertThrows(VenueAlreadyExistException.class, () -> venueService.createVenue(userId, request));
        verify(venueValidator).validateVenueCreation(request);
        verify(venueRepository, never()).save(any(Venue.class));
        verify(imageService, never()).createImages(any(), any(), any());
    }

    @Test
    void createVenue_ThrowsVenueMaxCapacityException() {
        doThrow(new VenueMaxCapacityException())
                .when(venueValidator)
                .validateVenueCreation(request);

        assertThrows(VenueMaxCapacityException.class, () -> venueService.createVenue(userId, request));

        verify(venueValidator).validateVenueCreation(request);
        verify(venueRepository, never()).save(any(Venue.class));
        verify(imageService, never()).createImages(any(), any(), any());
    }

    @Test
    void createVenue_ImageServiceFailure() {
        when(venueRepository.save(any(Venue.class))).thenReturn(venue);
        doThrow(new RuntimeException("Image service error"))
                .when(imageService)
                .createImages(any(), any(), any());

        Exception exception = assertThrows(RuntimeException.class, () -> venueService.createVenue(userId, request));
        assertEquals("Image service error", exception.getMessage());

        verify(venueValidator).validateVenueCreation(request);
        verify(venueRepository).save(any(Venue.class));
        verify(imageService).createImages(venue.getId(), request.getImages(), EntityType.VENUE);
    }
}