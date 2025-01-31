package dev.zbib.venueservice.service;

import dev.zbib.venueservice.builder.AmenityCategoryBuilder;
import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest;
import dev.zbib.venueservice.entity.AmenityCategory;
import dev.zbib.venueservice.repository.AmenityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityCategoryService {

    private final AmenityCategoryRepository amenityCategoryRepository;
    private final AmenityCategoryValidator amenityCategoryValidator;

    public void createAmenityCategory(AmenityCategoryCreationRequest requests) {
        amenityCategoryValidator.validateCreation(requests);
        List<AmenityCategory> amenityCategories = requests
                .getCategories()
                .stream()
                .map(AmenityCategoryBuilder::buildAmenityCategory)
                .toList();
        amenityCategoryRepository.saveAll(amenityCategories);
    }
}
