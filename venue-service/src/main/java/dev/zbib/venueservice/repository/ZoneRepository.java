package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {
}
