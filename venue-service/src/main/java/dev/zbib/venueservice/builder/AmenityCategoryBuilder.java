package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.AmenityCategoryCreationRequest;
import dev.zbib.venueservice.entity.AmenityCategory;

public class AmenityCategoryBuilder {

    public static AmenityCategory buildAmenityCategory(AmenityCategoryCreationRequest.AmenityCategory request) {
        return dev.zbib.venueservice.entity.AmenityCategory
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .sortOrder(0)
                .build();
    }
}
