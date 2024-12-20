package dev.zbib.providerservice.service;

import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.entity.Provider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProviderSpecification {

    public static Specification<Provider> getProviders(final ProviderFilterRequest filter) {
        return (Root<Provider> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //  availability filter
            Optional.ofNullable(filter.getAvailable())
                    .ifPresent(available -> predicates.add(criteriaBuilder.equal(root.get("available"), available)));

            //  service area filter
            Optional.ofNullable(filter.getServiceArea())
                    .filter(serviceArea -> !serviceArea.isEmpty())
                    .ifPresent(serviceArea -> predicates.add(criteriaBuilder.equal(
                            root.get("serviceArea"),
                            serviceArea)));

            //  hourly rate filters
            Optional.ofNullable(filter.getMinHourlyRate())
                    .filter(rate -> rate > 0)
                    .ifPresent(minRate -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("hourlyRate"),
                            minRate)));

            Optional.ofNullable(filter.getMaxHourlyRate())
                    .filter(rate -> rate > 0)
                    .ifPresent(maxRate -> predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get("hourlyRate"),
                            maxRate)));

            //  service type filter
            Optional.ofNullable(filter.getServiceType())
                    .ifPresent(serviceType -> predicates.add(criteriaBuilder.equal(
                            root.get("serviceType"),
                            serviceType)));

            //  rating filters
            Optional.ofNullable(filter.getMinRating())
                    .filter(rating -> rating >= 0)
                    .ifPresent(minRating -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("rating"),
                            minRating)));

            Optional.ofNullable(filter.getMaxRating())
                    .filter(rating -> rating <= 5)
                    .ifPresent(maxRating -> predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get("rating"),
                            maxRating)));

            // Combine all predicates
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
