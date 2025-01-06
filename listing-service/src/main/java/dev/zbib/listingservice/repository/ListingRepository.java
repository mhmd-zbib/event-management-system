package dev.zbib.listingservice.repository;

import dev.zbib.listingservice.entity.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListingRepository extends MongoRepository<Listing, String> {
}
