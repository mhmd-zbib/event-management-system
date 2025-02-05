package dev.zbib.venueservice.dto;

import dev.zbib.venueservice.entity.ZoneAmenity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class UpdateZoneAmenityRequest {

    List<UUID> addedAmenityId;
    List<UUID> removedAmenityId;

}
