package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for creating a new venue")
public class VenueCreationRequest {

    @NotBlank(message = "Venue name is required")
    @Size(min = 2, max = 100, message = "Venue name must be between 2 and 100 characters")
    @Schema(description = "Name of the venue", example = "Grand Ballroom", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Schema(description = "Detailed description of the venue",
            example = "A luxurious ballroom with modern amenities and classic design")
    private String description;

    @NotNull(message = "Maximum capacity is required")
    @Min(value = 1, message = "Maximum capacity must be at least 1")
    @Max(value = 100000, message = "Maximum capacity cannot exceed 100,000")
    @Schema(description = "Maximum number of people the venue can accommodate",
            example = "500",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int maxCapacity;

    @NotNull(message = "Booking timeline is required")
    @Min(value = 1, message = "Booking timeline must be at least 1")
    @Max(value = 365, message = "Booking timeline cannot exceed 365 days")
    @Schema(description = "Number of days in advance that the venue can be booked",
            example = "90",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int bookingTimeline;

    @NotNull(message = "Images are required")
    @Size(min = 5, max = 10, message = "Number of images must be between 5 and 10")
    @Valid
    @Schema(description = "List of images associated with the venue",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 10,
            minLength = 5)
    private List<ImageCreationRequest> images;
}