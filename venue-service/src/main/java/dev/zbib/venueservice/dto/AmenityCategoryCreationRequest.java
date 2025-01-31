package dev.zbib.venueservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AmenityCategoryCreationRequest {

    @NotEmpty(message = "At least one category is required")
    @Size(max = 100, message = "Cannot create more than 100 categories at once")
    @Valid
    private List<CategoryRequest> categories;

    @Getter
    @Setter
    public static class CategoryRequest {
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        private String name;

        @Size(max = 255, message = "Description cannot exceed 255 characters")
        private String description;

        @Pattern(regexp = "^/icons/[a-zA-Z0-9-_]+\\.(svg|png|jpg|jpeg)$",
                message = "Icon URL must be in format: /icons/filename.extension")
        @NotBlank(message = "Icon URL is required")
        private String iconUrl;

        @Min(value = 0, message = "Sort order must be non-negative")
        @Max(value = 999, message = "Sort order cannot exceed 999")
        private Integer sortOrder = 0; // Default value
    }
}