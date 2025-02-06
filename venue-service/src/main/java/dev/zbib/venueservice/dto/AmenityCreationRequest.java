package dev.zbib.venueservice.dto;

import dev.zbib.venueservice.entity.AmenityCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AmenityCreationRequest {

    @NotEmpty(message = "At least one amenity is required")
    @Size(max = 100,
          message = "Cannot create more than 100 amenities at once")
    @Valid
    private List<Amenity> amenities;

    @Getter
    @Setter
    public static class Amenity {

        private UUID categoryId;

        private String name;

        private String description;

        private String iconUrl;

    }
}
