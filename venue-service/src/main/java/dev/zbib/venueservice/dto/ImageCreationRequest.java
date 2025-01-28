package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Schema(description = "Request object for creating/uploading images")
public class ImageCreationRequest {

    @NotNull(message = "Entity ID is required")
    @Schema(description = "ID of the entity this image belongs to", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID entityId;

    @NotBlank(message = "URL is required")
    @Pattern(regexp = "^(https?)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL format")
    @Schema(description = "URL of the image", example = "https://example.com/images/zone1.jpg")
    private String url;

    @Min(value = 0, message = "Order must be non-negative")
    @Schema(description = "Display order of the image", example = "1")
    private int order;

    @Positive(message = "Size must be positive")
    @Max(value = 5242880, message = "File size cannot exceed 5MB")
    @Schema(description = "Size of the image in bytes", example = "1024000")
    private int size;

    @Min(value = 100, message = "Width must be at least 100 pixels")
    @Max(value = 4096, message = "Width cannot exceed 4096 pixels")
    @Schema(description = "Width of the image in pixels", example = "1920")
    private int width;

    @Min(value = 100, message = "Height must be at least 100 pixels")
    @Max(value = 4096, message = "Height cannot exceed 4096 pixels")
    @Schema(description = "Height of the image in pixels", example = "1080")
    private int height;
}
