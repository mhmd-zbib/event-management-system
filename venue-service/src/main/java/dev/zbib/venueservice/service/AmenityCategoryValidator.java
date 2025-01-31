package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest;
import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest.CategoryRequest;
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
    public void validateCreation(AmenityCategoryCreationRequest request) {
        List<CategoryRequest> categories = request.getCategories();
        validateUniqueness(categories);
        validateNotExisting(categories);
    }

    private void validateUniqueness(List<CategoryRequest> categories) {
        Set<String> names = categories
                .stream()
                .map(CategoryRequest::getName)
                .collect(Collectors.toSet());

        if (names.size() < categories.size()) {
            throw new IllegalArgumentException("DUPLICATE_CATEGORY_NAME");
        }

        Set<String> icons = categories
                .stream()
                .map(CategoryRequest::getIconUrl)
                .collect(Collectors.toSet());

        if (icons.size() < categories.size()) {
            throw new IllegalArgumentException("DUPLICATE_ICON_URL");
        }
    }

    private void validateNotExisting(List<CategoryRequest> categories) {
        Set<String> names = categories
                .stream()
                .map(CategoryRequest::getName)
                .collect(Collectors.toSet());

        if (amenityCategoryRepository.existsByNames(names)) {
            throw new IllegalArgumentException("CATEGORY_NAME_ALREADY_EXISTS");
        }
    }
}