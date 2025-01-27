package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneValidator {

    private final ZoneRepository zoneRepository;
    private final VenueValidator venueValidator;

    public void validateZoneCreation(ZoneCreationRequest request, Venue venue) {
        validateUniqueName(request, venue);
        validateBookingHours(request);
        venueValidator.validateVenueForZoneCreation(venue);

    }

    private void validateUniqueName(ZoneCreationRequest request, Venue venue) {
        if (zoneRepository.existsByNameAndVenueId(request.getName(), venue.getId())) {
        }
    }

    private void validateBookingHours(ZoneCreationRequest request) {
        int minBookingHours = request.getMinBookingDuration();
        int maxBookingHours = request.getMaxBookingDuration();
        if (minBookingHours > maxBookingHours) {
        }
    }
}
