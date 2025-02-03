package dev.zbib.venueservice.validator;

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

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VenueValidator {

    private static final int MIN_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100000;
    private static final int MAX_ZONES_PER_VENUE = 20;

    private final VenueRepository venueRepository;
    private final ImageValidator imageValidator;

    public void validateVenueCreation(VenueCreationRequest request) {
        validateUniqueName(request);
        imageValidator.validateImageCreation(request.getImages());
    }

    private void validateUniqueName(VenueCreationRequest request) {
        if (venueRepository.existsByName(request.getName())) {
            throw new VenueNameAlreadyExistException();
        }
    }
}
