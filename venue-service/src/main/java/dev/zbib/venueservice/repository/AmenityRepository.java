package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmenityRepository extends JpaRepository<Amenity, UUID> {
}
