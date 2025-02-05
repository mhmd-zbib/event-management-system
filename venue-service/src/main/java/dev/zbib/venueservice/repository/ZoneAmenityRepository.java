package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.ZoneAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ZoneAmenityRepository extends JpaRepository<ZoneAmenity, Long> {
    List<ZoneAmenity> findByZoneId(UUID zoneId);

    void deleteByZoneIdAndAmenityIdIn(UUID id, List<UUID> amenityIdsToRemove);

    @Query("SELECT CASE WHEN COUNT(za) > 0 THEN true ELSE false END FROM ZoneAmenity za " +
            "WHERE za.zone.id = :zoneId AND za.amenity.id IN :amenityIds")
    boolean existsAnyByZoneIdAndAmenityIdIn(
            @Param("zoneId")
            UUID zoneId,
            @Param("amenityIds")
            List<UUID> amenityIds
    );

    @Query("SELECT CASE WHEN COUNT(DISTINCT za.amenity.id) = " +
            "(SELECT COUNT(DISTINCT a.id) FROM Amenity a WHERE a.id IN :amenityIds) " +
            "THEN true ELSE false END " +
            "FROM ZoneAmenity za " +
            "WHERE za.zone.id = :zoneId AND za.amenity.id IN :amenityIds")
    boolean existsAllByZoneIdAndAmenityIdIn(
            @Param("zoneId")
            UUID zoneId,
            @Param("amenityIds")
            List<UUID> amenityIds
    );
}
