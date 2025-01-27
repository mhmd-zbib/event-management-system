package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.enums.VenueStatus;
import dev.zbib.venueservice.exception.VenueMaxZonesException;
import dev.zbib.venueservice.exception.VenueNameAlreadyExistException;
import dev.zbib.venueservice.exception.VenueStatusInvalidForZonesException;
import dev.zbib.venueservice.repository.VenueRepository;
import dev.zbib.venueservice.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueValidator {

    private static final int MIN_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100000;
    private static final int MAX_ZONES_PER_VENUE = 20;
    private final VenueRepository venueRepository;
    private final ZoneRepository zoneRepository;

    public void validateVenueCreation(VenueCreationRequest request) {
        validateUniqueName(request);
    }

    public void validateVenueForZoneCreation(Venue venue) {
        validateMaxZoneLimit(venue);
        validateVenueEligibility(venue);
    }

    private void validateUniqueName(VenueCreationRequest request) {
        if (venueRepository.existsByName(request.getName())) {
            throw new VenueNameAlreadyExistException();
        }
    }

    private void validateMaxZoneLimit(Venue venue) {
        long currentZoneCount = zoneRepository.countByVenueId(venue.getId());
        if (currentZoneCount > MAX_ZONES_PER_VENUE) {
            throw new VenueMaxZonesException();
        }
    }

    private void validateVenueEligibility(Venue venue) {
        if (venue.getStatus() != VenueStatus.ACTIVE && venue.getStatus() != VenueStatus.UNDER_MAINTENANCE) {
            throw new VenueStatusInvalidForZonesException();
        }
    }
}
