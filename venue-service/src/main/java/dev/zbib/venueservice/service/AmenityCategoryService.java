package dev.zbib.venueservice.service;

import dev.zbib.venueservice.builder.AmenityCategoryBuilder;
import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest;
import dev.zbib.venueservice.entity.AmenityCategory;
import dev.zbib.venueservice.repository.AmenityCategoryRepository;
import dev.zbib.venueservice.validator.AmenityCategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityCategoryService {

    private final AmenityCategoryRepository amenityCategoryRepository;
    private final AmenityCategoryValidator amenityCategoryValidator;

    public void createAmenityCategory(AmenityCategoryCreationRequest requests) {
        amenityCategoryValidator.validateAmenityCategoryCreation(requests);
        List<AmenityCategory> amenityCategories = requests
                .getCategories()
                .stream()
                .map(AmenityCategoryBuilder::buildAmenityCategory)
                .toList();
        amenityCategoryRepository.saveAll(amenityCategories);
    }


    public Map<UUID, AmenityCategory> getAmenityCategoriesMapById(List<UUID> categoryIds) {
        List<AmenityCategory> categories = amenityCategoryRepository.findAllById(categoryIds);
        if (categories.size() != categoryIds.size()) {
            Set<UUID> foundIds = categories
                    .stream()
                    .map(AmenityCategory::getId)
                    .collect(Collectors.toSet());
            Set<UUID> missingIds = categoryIds
                    .stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toSet());

            throw new IllegalArgumentException("error");
        }

        return categories
                .stream()
                .collect(Collectors.toMap(
                        AmenityCategory::getId,
                        category -> category
                ));
    }

}
