package dev.zbib.venueservice.service;

import dev.zbib.venueservice.builder.VenueBuilder;
import dev.zbib.venueservice.dto.VenueListResponse;
import dev.zbib.venueservice.dto.VenueQuery;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.repository.VenueQueryRepository;
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
public class VenueQueryService {

    private final MongoTemplate mongoTemplate;
    private final VenueQueryRepository venueQueryRepository;


    public Page<VenueListResponse> getVenues(VenueQuery filter, Pageable pageable) {
        Query query = venueQueryRepository.createFilterQuery(filter, pageable);
        return executeQuery(query, pageable);
    }

    public Page<VenueListResponse> getVenuesByOwnerId(String userId, Pageable pageable) {
        Query query = venueQueryRepository.createUserQuery(userId);
        return executeQuery(query, pageable);
    }

    private Page<VenueListResponse> executeQuery(Query query, Pageable pageable) {
        List<Venue> venues = mongoTemplate.find(query, Venue.class);
        long count = mongoTemplate.count(query, Venue.class);
        PageImpl<Venue> page = new PageImpl<>(venues, pageable, count);
        return page.map(VenueBuilder::buildVenueListResponse);
    }
}
