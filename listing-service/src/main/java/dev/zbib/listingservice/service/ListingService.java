package dev.zbib.listingservice.service;

import dev.zbib.listingservice.builder.ListingBuilder;
import dev.zbib.listingservice.dto.CreateListingRequest;
import dev.zbib.listingservice.dto.ListingFilter;
import dev.zbib.listingservice.dto.ListingListResponse;
import dev.zbib.listingservice.dto.ListingResponse;
import dev.zbib.listingservice.entity.Listing;
import dev.zbib.listingservice.exception.ListingNotFoundException;
import dev.zbib.listingservice.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.zbib.listingservice.builder.ListingBuilder.buildListing;
import static dev.zbib.listingservice.builder.ListingBuilder.buildListingResponse;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final ListSpecificationService listSpecificationService;

    public void createListing(String userId, CreateListingRequest req) {
        Listing listing = buildListing(userId, req);
        listingRepository.save(listing);
    }

    public ListingResponse getListing(UUID id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
        return buildListingResponse(listing);
    }

    public Page<ListingListResponse> getListings(ListingFilter filter, Pageable pageable) {
        Specification<Listing> specification = listSpecificationService.createListingSpecification(filter);
        Page<Listing> listings = listingRepository.findAll(specification, pageable);
        return listings.map(ListingBuilder::buildListingListResponse);
    }

    public Boolean getAvailability(UUID id) {
        Listing listing = getListingEntity(id);
        return listing.isAvailable() && listing.getStock() > 0;
    }

    private Listing getListingEntity(UUID id) {
        return listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
    }

    public Page<ListingListResponse> getListingsByUserId(String userId, Pageable pageable) {
        Page<Listing> listings = listingRepository.findByUserId(userId, pageable);
        return listings.map(ListingBuilder::buildListingListResponse);
    }
}
