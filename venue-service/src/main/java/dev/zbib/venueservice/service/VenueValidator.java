package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.exception.VenueAlreadyExistException;
import dev.zbib.venueservice.exception.VenueMaxCapacityException;
import dev.zbib.venueservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueValidator {

    private static final int MIN_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100000;

    private final VenueRepository venueRepository;

    public void validateVenueCreation(VenueCreationRequest request) {
        validateCapacity(request);
        validateUniqueName(request);
    }

    private void validateUniqueName(VenueCreationRequest request) {
        if (venueRepository.existsByName(request.getName())) {
            throw new VenueAlreadyExistException();
        }
    }

    private void validateCapacity(VenueCreationRequest request) {
        if (request.getMaxCapacity() < MIN_CAPACITY || request.getMaxCapacity() > MAX_CAPACITY) {
            throw new VenueMaxCapacityException();
        }
    }
}
