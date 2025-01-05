package dev.zbib.listingservice.repository;

import dev.zbib.listingservice.entity.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface ListingRepository extends MongoRepository<Listing, String> {
}
