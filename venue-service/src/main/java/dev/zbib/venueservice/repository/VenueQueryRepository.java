package dev.zbib.venueservice.repository;

import dev.zbib.venueservice.dto.VenueQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class VenueQueryRepository {

    public Query createFilterQuery(VenueQuery filter, Pageable pageable) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (filter.getMinPrice() != null || filter.getMaxPrice() != null) {
            Criteria priceCriteria = Criteria.where("price");
            if (filter.getMinPrice() != null) {
                priceCriteria.gte(filter.getMinPrice());
            }
            if (filter.getMaxPrice() != null) {
                priceCriteria.lte(filter.getMaxPrice());
            }
            criteriaList.add(priceCriteria);
        }
        if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
            criteriaList.add(Criteria.where("category").is(filter.getCategory()));
        }
        if (filter.getMinCapacity() != null || filter.getMaxCapacity() != null) {
            Criteria capacityCriteria = Criteria.where("capacity");
            if (filter.getMinCapacity() != null) {
                capacityCriteria.gte(filter.getMinCapacity());
            }
            if (filter.getMaxCapacity() != null) {
                capacityCriteria.lte(filter.getMaxCapacity());
            }
            criteriaList.add(capacityCriteria);
        }
        if (filter.getMinRating() != null || filter.getMaxRating() != null) {
            Criteria ratingCriteria = Criteria.where("rating");
            if (filter.getMinRating() != null) {
                ratingCriteria.gte(filter.getMinRating());
            }
            if (filter.getMaxRating() != null) {
                ratingCriteria.lte(filter.getMaxRating());
            }
            criteriaList.add(ratingCriteria);
        }
        if (filter.getAvailable() != null) {
            criteriaList.add(Criteria.where("available").is(filter.getAvailable()));
        }
        if (filter.getIsFeatured() != null) {
            criteriaList.add(Criteria.where("isFeatured").is(filter.getIsFeatured()));
        }
        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }
        return query.with(pageable);
    }

    public Query createUserQuery(String ownerId) {
        return new Query(Criteria.where("ownerId").is(ownerId));
    }
}
