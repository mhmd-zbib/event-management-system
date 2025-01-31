package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.AmenityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface AmenityCategoryRepository extends JpaRepository<AmenityCategory, UUID> {
    @Query("SELECT CASE WHEN COUNT(ac) > 0 THEN true ELSE false END FROM AmenityCategory ac WHERE ac.name IN :names")
    boolean existsByNames(
            @Param("names")
            Set<String> names);
}
