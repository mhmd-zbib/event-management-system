package dev.zbib.venueservice.unit.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.exception.VenueNameAlreadyExistException;
import dev.zbib.venueservice.exception.VenueMaxCapacityException;
import dev.zbib.venueservice.repository.VenueRepository;
import dev.zbib.venueservice.validator.VenueValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VenueValidatorUnitTest {

    @Mock
    private VenueRepository venueRepository;

    @InjectMocks
    private VenueValidator venueValidator;

    private VenueCreationRequest request;

    @BeforeEach
    void setUp() {
        request = VenueCreationRequest
                .builder()
                .name("Test Venue")
                .description("Test Description")
                .maxCapacity(50)
                .bookingTimeline(30)
                .build();
    }

    @Test
    void validateVenueCreation_Success() {
        when(venueRepository.existsByName(anyString())).thenReturn(false);
        assertDoesNotThrow(() -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_NameAlreadyExists() {
        when(venueRepository.existsByName(anyString())).thenReturn(true);
        assertThrows(VenueNameAlreadyExistException.class, () -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_CapacityBelowMinimum() {
        request.setMaxCapacity(5);
        assertThrows(VenueMaxCapacityException.class, () -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_CapacityAboveMaximum() {
        request.setMaxCapacity(200000);
        assertThrows(VenueMaxCapacityException.class, () -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_MinimumCapacity() {
        request.setMaxCapacity(10);
        when(venueRepository.existsByName(anyString())).thenReturn(false);
        assertDoesNotThrow(() -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_MaximumCapacity() {
        request.setMaxCapacity(100000);
        when(venueRepository.existsByName(anyString())).thenReturn(false);
        assertDoesNotThrow(() -> venueValidator.validateVenueCreation(request));
    }
}