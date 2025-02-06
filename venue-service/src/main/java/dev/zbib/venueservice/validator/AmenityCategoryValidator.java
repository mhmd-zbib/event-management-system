package dev.zbib.venueservice.validator;

import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest;
import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest.AmenityCategory;
import dev.zbib.venueservice.repository.AmenityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityCategoryValidator {

    private final AmenityCategoryRepository amenityCategoryRepository;

    @Transactional(readOnly = true)
    public void validateAmenityCategoryCreation(AmenityCategoryCreationRequest request) {
        List<AmenityCategory> categories = request.getCategories();
        validateUniqueness(categories);
        validateNotExisting(categories);
    }

    private void validateUniqueness(List<AmenityCategory> categories) {
        Set<String> names = categories
                .stream()
                .map(AmenityCategory::getName)
                .collect(Collectors.toSet());

        if (names.size() < categories.size()) {
            throw new IllegalArgumentException("DUPLICATE_CATEGORY_NAME");
        }

        Set<String> icons = categories
                .stream()
                .map(AmenityCategory::getIconUrl)
                .collect(Collectors.toSet());

        if (icons.size() < categories.size()) {
            throw new IllegalArgumentException("DUPLICATE_ICON_URL");
        }
    }

    private void validateNotExisting(List<AmenityCategory> categories) {
        Set<String> names = categories
                .stream()
                .map(AmenityCategory::getName)
                .collect(Collectors.toSet());

        if (amenityCategoryRepository.existsByNames(names)) {
            throw new IllegalArgumentException("CATEGORY_NAME_ALREADY_EXISTS");
        }
    }
}