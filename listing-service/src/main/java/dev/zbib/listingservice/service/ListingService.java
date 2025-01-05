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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.listingservice.builder.ListingBuilder.buildListing;
import static dev.zbib.listingservice.builder.ListingBuilder.buildListingResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final MongoTemplate mongoTemplate;


    public void createListing(String userId, CreateListingRequest req) {
        Listing listing = buildListing(userId, req);
        listingRepository.save(listing);
    }

    public ListingResponse getListing(String id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
        return buildListingResponse(listing);
    }public Page<ListingListResponse> getListings(ListingFilter filter, Pageable pageable) {
        log.info("Filter - Category: {}, Max Price: {}, Min Price: {}",
                filter.getCategory(), filter.getMaxPrice(), filter.getMinPrice());

        Query query = new Query();

        // Apply price range filter: minPrice and maxPrice
        if (filter.getMinPrice() != null || filter.getMaxPrice() != null) {
            Criteria priceCriteria = Criteria.where("price");

            if (filter.getMinPrice() != null) {
                priceCriteria.gte(filter.getMinPrice());
            }

            if (filter.getMaxPrice() != null) {
                priceCriteria.lte(filter.getMaxPrice());
            }

            query.addCriteria(priceCriteria);
        }

        // Filter by category if provided
        if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
            query.addCriteria(Criteria.where("category").is(filter.getCategory()));
        }

        // Apply pagination and sorting based on the Pageable object
        query.with(pageable);

        // Execute the query and get the results
        List<Listing> listings = mongoTemplate.find(query, Listing.class);
        long count = mongoTemplate.count(query, Listing.class);

        PageImpl<Listing> page = new PageImpl<>(listings, pageable, count);
        return page.map(ListingBuilder::buildListingListResponse);
    }

    public Boolean getAvailability(String id) {
        Listing listing = getListingEntity(id);
        return listing.isAvailable() && listing.getStock() > 0;
    }

    private Listing getListingEntity(String id) {
        return listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id));
    }

//    public Page<ListingListResponse> getListingsByUserId(String userId, Pageable pageable) {
//        Page<Listing> listings = listingRepository.findByUserId(userId, pageable);
//        return listings.map(ListingBuilder::buildListingListResponse);
//    }
}
