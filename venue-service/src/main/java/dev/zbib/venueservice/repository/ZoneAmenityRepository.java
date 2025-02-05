package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.ZoneAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ZoneAmenityRepository extends JpaRepository<ZoneAmenity, Long> {
}
