package dev.zbib.venueservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response object containing created zone details")
public class ZoneCreationResponse {
    @Schema(description = "Unique identifier of the created zone", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Name of the zone", example = "VIP Lounge")
    private String name;

    @Schema(description = "Detailed description of the zone", example = "Premium lounge area with scenic view")
    private String description;
}