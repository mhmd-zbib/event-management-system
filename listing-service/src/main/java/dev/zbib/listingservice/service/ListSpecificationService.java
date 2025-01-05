package dev.zbib.listingservice.service;

import dev.zbib.listingservice.dto.ListingFilter;
import dev.zbib.listingservice.entity.Listing;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ListSpecificationService {

    public Specification<Listing> createListingSpecification(ListingFilter filter) {
        Specification<Listing> specification = Specification.where(null);
        if (filter.getUserId() != null) {
            specification = specification.and(belongsToUser(filter.getUserId()));
        }
        if (filter.getMinPrice() != null || filter.getMaxPrice() != null) {
            specification = specification.and(isPriceBetween(filter.getMinPrice(), filter.getMaxPrice()));
        }
        if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
            specification = specification.and(hasCategory(filter.getCategory()));
        }

        return specification;
    }

    public Specification<Listing> belongsToUser(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("userId"), userId);
        };
    }

    public Specification<Listing> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

    public Specification<Listing> isPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}

