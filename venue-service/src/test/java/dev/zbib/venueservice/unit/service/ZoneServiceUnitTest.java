package dev.zbib.venueservice.unit.service;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.entity.StatusTypes;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.enums.VenueStatus;
import dev.zbib.venueservice.repository.ZoneRepository;
import dev.zbib.venueservice.service.ImageService;
import dev.zbib.venueservice.service.VenueService;
import dev.zbib.venueservice.service.ZoneService;
import dev.zbib.venueservice.validator.ZoneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZoneServiceUnitTest {

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private VenueService venueService;

    @Mock
    private ZoneValidator zoneValidator;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ZoneService zoneService;

    private UUID venueId;
    private Venue venue;
    private ZoneCreationRequest request;
    private Zone savedZone;


    @BeforeEach
    void setUp() {

        StatusTypes statusType = new StatusTypes();
        statusType.setId(UUID.randomUUID());
        statusType.setType(EntityType.VENUE);
        statusType.setName(VenueStatus.PENDING.name());
        statusType.setDisplayName(VenueStatus.PENDING.getDisplayName());


        venueId = UUID.randomUUID();
        venue = Venue
                .builder()
                .id(venueId)
                .name("Test Venue")
                .status(statusType)
                .build();

        request = new ZoneCreationRequest();
        request.setName("Test Zone");
        request.setCapacity(100);
        request.setLength(new BigDecimal("10.0"));
        request.setWidth(new BigDecimal("10.0"));
        request.setHeight(new BigDecimal("3.0"));
        request.setHourlyFee(new BigDecimal("50.0"));
        request.setExcessFee(new BigDecimal("10.0"));
        request.setMinBookingDuration(1);
        request.setMaxBookingDuration(24);


        List<ImageCreationRequest> images = Arrays.asList(new ImageCreationRequest());
        request.setImages(images);

        savedZone = Zone
                .builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .capacity(request.getCapacity())
                .venue(venue)
                .build();
    }

    @Test
    void createZone_ShouldCreateZoneSuccessfully() {
        when(venueService.getVenueById(venueId)).thenReturn(venue);
        when(zoneRepository.save(any(Zone.class))).thenReturn(savedZone);

        ZoneCreationResponse response = zoneService.createZone(venueId, request);

        assertNotNull(response);
        assertEquals(savedZone.getId(), response.getId());
        assertEquals(savedZone.getName(), response.getName());

        verify(zoneValidator).validateZoneCreation(eq(request), eq(venue));
        verify(imageService).createImages(eq(savedZone.getId()), eq(request.getImages()), eq(EntityType.ZONE));
    }
}