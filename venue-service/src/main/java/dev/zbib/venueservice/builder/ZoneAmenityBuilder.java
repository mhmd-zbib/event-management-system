package dev.zbib.venueservice.builder;

import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.entity.Zone;
import dev.zbib.venueservice.entity.ZoneAmenity;

public class ZoneAmenityBuilder {

    public static ZoneAmenity buildZoneAmenity(Zone zone, Amenity amenity) {
        return ZoneAmenity
                .builder()
                .amenity(amenity)
                .zone(zone)
                .build();
    }
}
