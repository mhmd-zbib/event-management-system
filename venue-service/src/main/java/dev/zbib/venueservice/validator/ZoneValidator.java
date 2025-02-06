package dev.zbib.venueservice.validator;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.exception.ZoneNameAlreadyExist;
import dev.zbib.venueservice.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ZoneValidator {

    private final ZoneRepository zoneRepository;
    private final ZoneAmenityValidator zoneAmenityValidator;
    private final VenueValidator venueValidator;

    public void validateZoneCreation(ZoneCreationRequest request, Venue venue, List<Amenity> amenities) {
        venueValidator.validateZoneCreation(venue);
        zoneAmenityValidator.validateZoneAmenitiesCreation(amenities);
        validateUniqueName(request, venue);
    }

    private void validateUniqueName(ZoneCreationRequest request, Venue venue) {
        if (zoneRepository.existsByNameAndVenueId(request.getName(), venue.getId())) {
            throw new ZoneNameAlreadyExist();
        }
    }


}
