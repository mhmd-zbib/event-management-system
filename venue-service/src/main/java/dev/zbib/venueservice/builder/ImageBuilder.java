package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.ImageRequest;
import dev.zbib.venueservice.entity.Image;
import dev.zbib.venueservice.enums.EntityType;

import java.util.UUID;

public class ImageBuilder {

    public static Image buildImage(UUID entityId, ImageRequest request, EntityType entityType) {
        return Image
                .builder()
                .entityId(entityId)
                .url(request.getUrl())
                .entityType(entityType)
                .width(request.getWidth())
                .height(request.getHeight())
                .size(request.getSize())
                .order(request.getOrder())
                .build();
    }
}
