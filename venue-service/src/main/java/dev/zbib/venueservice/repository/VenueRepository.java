package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {
    boolean existsByName(String name);
}
