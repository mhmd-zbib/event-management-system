package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for creating a new venue")
public class VenueCreationRequest {

    @Schema(description = "Name of the venue", example = "Grand Ballroom", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Detailed description of the venue",
            example = "A luxurious ballroom with modern amenities and classic design")
    private String description;

    @Schema(description = "Maximum number of people the venue can accommodate",
            example = "500",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int maxCapacity;

    @Schema(description = "Number of days in advance that the venue can be booked",
            example = "90",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int bookingTimeline;

    @Schema(description = "List of images associated with the venue", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ImageCreationRequest> images;
}