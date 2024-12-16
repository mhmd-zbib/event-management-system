package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.entity.Provider;
import org.springframework.data.jpa.domain.Specification;

public class ProviderSpecification {

    public static Specification<Provider> withRatingRange(
            Double minRating,
            Double maxRating) {
        return (root, query, criteriaBuilder) -> {
            if (minRating == null && maxRating == null) {
                return null;
            }
            if (minRating != null && maxRating != null) {
                return criteriaBuilder.between(root.get("rating"), minRating, maxRating);
            }
            if (minRating != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), maxRating);
        };
    }


    public static Specification<Provider> withHourlyRateRange(
            Double minRate,
            Double maxRate) {
        return (root, query, criteriaBuilder) -> {
            if (minRate == null && maxRate == null) {
                return null;
            }
            if (minRate != null && maxRate != null) {
                return criteriaBuilder.between(root.get("hourlyRate"), minRate, maxRate);
            }
            if (minRate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("hourlyRate"), minRate);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("hourlyRate"), maxRate);
        };
    }


    public static Specification<Provider> withAvailability(Boolean availability) {
        return (root, query, criteriaBuilder) -> {
            if (availability == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("availability"), availability);
        };
    }


    public static Specification<Provider> withServiceArea(String serviceArea) {
        return (root, query, criteriaBuilder) -> {
            if (serviceArea == null || serviceArea.isEmpty()) return null;
            return criteriaBuilder.equal(root.get("serviceArea"), serviceArea);
        };
    }


}