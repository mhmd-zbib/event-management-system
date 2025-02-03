package dev.zbib.venueservice.validator;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.exception.ZoneNameAlreadyExist;
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
        venueValidator.validateVenueForZoneCreation(venue);

    }

    private void validateUniqueName(ZoneCreationRequest request, Venue venue) {
        if (zoneRepository.existsByNameAndVenueId(request.getName(), venue.getId())) {
            throw new ZoneNameAlreadyExist();
        }
    }
}
