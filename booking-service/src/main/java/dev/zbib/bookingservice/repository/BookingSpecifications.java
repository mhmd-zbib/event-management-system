package dev.zbib.bookingservice.repository;

import dev.zbib.bookingservice.dto.BookingFilterCriteria;
import dev.zbib.bookingservice.model.Booking;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class BookingSpecifications {
    
    public static Specification<Booking> withCriteria(BookingFilterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (criteria.getUserId() != null) {
                predicates.add(cb.equal(root.get("userId"), criteria.getUserId()));
            }
            
            if (criteria.getProviderId() != null) {
                predicates.add(cb.equal(root.get("providerId"), criteria.getProviderId()));
            }
            
            if (criteria.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), criteria.getStatus()));
            }
            
            if (criteria.getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("scheduledStartTime"), criteria.getStartDate()));
            }
            
            if (criteria.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("scheduledEndTime"), criteria.getEndDate()));
            }
            
            if (criteria.getMinRating() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), criteria.getMinRating()));
            }
            
            if (criteria.getMaxRating() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("rating"), criteria.getMaxRating()));
            }
            
            if (criteria.getMinCost() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("totalCost"), criteria.getMinCost()));
            }
            
            if (criteria.getMaxCost() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("totalCost"), criteria.getMaxCost()));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
} 