package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.entity.Venue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VenueRepository extends MongoRepository<Venue, String> {
}
