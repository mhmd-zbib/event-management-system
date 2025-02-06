package dev.zbib.venueservice.service;

import dev.zbib.venueservice.builder.AmenityBuilder;
import dev.zbib.venueservice.dto.AmenityCreationRequest;
import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.AmenityCategory;
import dev.zbib.venueservice.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityCategoryService amenityCategoryService;

    public void createAmenity(AmenityCreationRequest request) {
        List<UUID> categoryIds = request
                .getAmenities()
                .stream()
                .map(AmenityCreationRequest.Amenity::getCategoryId)
                .toList();
        Map<UUID, AmenityCategory> categoriesMap = amenityCategoryService.getAmenityCategoriesMapById(categoryIds);
        List<Amenity> amenities = request
                .getAmenities()
                .stream()
                .map(amenityRequest -> {    
                    AmenityCategory category = categoriesMap.get(amenityRequest.getCategoryId());
                    if (category == null) {
                        throw new IllegalArgumentException("");
                    }
                    return AmenityBuilder.buildAmenity(amenityRequest, category);
                })
                .toList();

        amenityRepository.saveAll(amenities);
    }

    public List<Amenity> getAmenityByIds(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) return null;
        return amenityRepository.findAllById(ids);
    }
}
