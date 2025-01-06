package dev.zbib.listingservice.service;

import dev.zbib.listingservice.builder.ListingBuilder;
import dev.zbib.listingservice.dto.ListingListResponse;
import dev.zbib.listingservice.dto.ListingQuery;
import dev.zbib.listingservice.entity.Listing;
import dev.zbib.listingservice.repository.ListingQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingQueryService {

    private final MongoTemplate mongoTemplate;
    private final ListingQueryRepository listingQueryRepository;


    public Page<ListingListResponse> getListings(ListingQuery filter, Pageable pageable) {
        Query query = listingQueryRepository.createFilterQuery(filter, pageable);
        return executeQuery(query, pageable);
    }

    public Page<ListingListResponse> getListingsByUserId(String userId, Pageable pageable) {
        Query query = listingQueryRepository.createUserQuery(userId);
        return executeQuery(query, pageable);
    }

    private Page<ListingListResponse> executeQuery(Query query, Pageable pageable) {
        List<Listing> listings = mongoTemplate.find(query, Listing.class);
        long count = mongoTemplate.count(query, Listing.class);
        PageImpl<Listing> page = new PageImpl<>(listings, pageable, count);
        return page.map(ListingBuilder::buildListingListResponse);
    }

}
