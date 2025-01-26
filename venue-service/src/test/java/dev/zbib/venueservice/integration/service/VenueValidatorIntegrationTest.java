package dev.zbib.venueservice.integration.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.exception.VenueAlreadyExistException;
import dev.zbib.venueservice.exception.VenueMaxCapacityException;
import dev.zbib.venueservice.repository.VenueRepository;
import dev.zbib.venueservice.service.VenueValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class VenueValidatorIntegrationTest {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private VenueValidator venueValidator;

    private VenueCreationRequest request;

    @BeforeEach
    void setUp() {
        venueRepository.deleteAll();
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
        assertDoesNotThrow(() -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_NameAlreadyExists() {
        // Create a venue with the same name
        Venue existingVenue = Venue
                .builder()
                .name(request.getName())
                .description("Existing Description")
                .maxCapacity(100)
                .bookingTimeline(30)
                .build();
        venueRepository.save(existingVenue);

        assertThrows(VenueAlreadyExistException.class, () -> venueValidator.validateVenueCreation(request));
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
        assertDoesNotThrow(() -> venueValidator.validateVenueCreation(request));
    }

    @Test
    void validateVenueCreation_MaximumCapacity() {
        request.setMaxCapacity(100000);
        assertDoesNotThrow(() -> venueValidator.validateVenueCreation(request));
    }
}