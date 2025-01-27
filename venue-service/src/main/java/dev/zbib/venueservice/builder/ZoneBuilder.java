package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.dto.ZoneCreationRequest;
import dev.zbib.venueservice.dto.ZoneCreationResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.entity.Zone;

public class ZoneBuilder {

    public static Zone buildZone(ZoneCreationRequest request, Venue venue) {
        return Zone
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .capacity(request.getCapacity())
                .length(request.getLength())
                .width(request.getWidth())
                .height(request.getHeight())
                .hourlyFee(request.getHourlyFee())
                .excessFee(request.getExcessFee())
                .minBookingDuration(request.getMinBookingDuration())
                .maxBookingDuration(request.getMaxBookingDuration())
                .venue(venue)
                .build();
    }

    public static ZoneCreationResponse buildZoneCreationResponse(Zone zone) {
        return ZoneCreationResponse
                .builder()
                .id(zone.getId())
                .name(zone.getName())
                .description(zone.getDescription())
                .build();
    }
}
