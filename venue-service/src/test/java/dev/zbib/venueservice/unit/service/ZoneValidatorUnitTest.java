package dev.zbib.venueservice.unit.service;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.entity.StatusTypes;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.enums.VenueStatus;
import dev.zbib.venueservice.exception.ZoneNameAlreadyExist;
import dev.zbib.venueservice.repository.ZoneRepository;
import dev.zbib.venueservice.validator.VenueValidator;
import dev.zbib.venueservice.validator.ZoneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZoneValidatorUnitTest {

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private VenueValidator venueValidator;

    @InjectMocks
    private ZoneValidator zoneValidator;

    private Venue venue;
    private ZoneCreationRequest request;

    @BeforeEach
    void setUp() {

        StatusTypes statusType = new StatusTypes();
        statusType.setId(UUID.randomUUID());
        statusType.setType(EntityType.VENUE);
        statusType.setName(VenueStatus.PENDING.name());
        statusType.setDisplayName(VenueStatus.PENDING.getDisplayName());


        venue = Venue
                .builder()
                .id(UUID.randomUUID())
                .name("Test Venue")
                .status(statusType)
                .build();

        request = new ZoneCreationRequest();
        request.setName("Test Zone");
    }

    @Test
    void validateZoneCreation_WhenNameIsUnique_ShouldNotThrowException() {
        when(zoneRepository.existsByNameAndVenueId(eq(request.getName()), eq(venue.getId()))).thenReturn(false);

        assertDoesNotThrow(() -> zoneValidator.validateZoneCreation(request, venue));
    }

    @Test
    void validateZoneCreation_WhenNameExists_ShouldThrowZoneNameAlreadyExist() {
        when(zoneRepository.existsByNameAndVenueId(eq(request.getName()), eq(venue.getId()))).thenReturn(true);

        assertThrows(ZoneNameAlreadyExist.class, () -> zoneValidator.validateZoneCreation(request, venue));
    }

    @Test
    void validateZoneCreation_ShouldCallVenueValidator() {
        when(zoneRepository.existsByNameAndVenueId(any(), any())).thenReturn(false);

        zoneValidator.validateZoneCreation(request, venue);
    }
}