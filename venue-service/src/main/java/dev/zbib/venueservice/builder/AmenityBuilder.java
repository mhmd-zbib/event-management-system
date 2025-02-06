package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.AmenityCreationRequest;
import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.AmenityCategory;

public class AmenityBuilder {

    public static Amenity buildAmenity(AmenityCreationRequest.Amenity request, AmenityCategory category) {
        return Amenity
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .iconUrl(request.getIconUrl())
                .build();
    }
}
