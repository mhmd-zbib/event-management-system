package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Schema(description = "Request object for creating/uploading images")
public class ImageCreationRequest {
    @Schema(description = "ID of the entity this image belongs to", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID entityId;

    @Schema(description = "URL of the image", example = "https://example.com/images/zone1.jpg")
    private String url;

    @Schema(description = "Display order of the image", example = "1")
    private int order;

    @Schema(description = "Size of the image in bytes", example = "1024000")
    private int size;

    @Schema(description = "Width of the image in pixels", example = "1920")
    private int width;

    @Schema(description = "Height of the image in pixels", example = "1080")
    private int height;
}