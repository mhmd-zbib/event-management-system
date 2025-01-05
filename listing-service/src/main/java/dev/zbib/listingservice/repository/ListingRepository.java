package dev.zbib.listingservice.repository;

import dev.zbib.listingservice.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {
}
