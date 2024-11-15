package dev.zbib.providerservice.specification;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import org.springframework.data.jpa.domain.Specification;

public class ProviderSpecification {

    // Filter by service type
    public static Specification<Provider> hasServiceType(ServiceType serviceType) {
        return (root, query, cb) -> serviceType != null ? cb.equal(root.get("serviceType"), serviceType) : null;
    }

    // Filter by availability
    public static Specification<Provider> isAvailable(Boolean available) {
        return (root, query, cb) -> available != null ? cb.equal(root.get("available"), available) : null;
    }

    // Filter by hourly rate
    public static Specification<Provider> hasHourlyRate(Double hourlyRate) {
        return (root, query, cb) -> hourlyRate != null ? cb.equal(root.get("hourlyRate"), hourlyRate) : null;
    }

    // Filter by service area
    public static Specification<Provider> hasServiceArea(String serviceArea) {
        return (root, query, cb) -> serviceArea != null ? cb.like(
                cb.lower(root.get("serviceArea")),
                "%" + serviceArea.toLowerCase() + "%") : null;
    }

    // Create filter combining all filters (AND logic)
    public static Specification<Provider> createFilter(
            ServiceType serviceType,
            Boolean available,
            Double hourlyRate,
            String serviceArea) {

        Specification<Provider> spec = Specification.where(null);

        if (serviceType != null) {
            spec = spec.and(hasServiceType(serviceType));
        }

        if (available != null) {
            spec = spec.and(isAvailable(available));
        }

        if (hourlyRate != null) {
            spec = spec.and(hasHourlyRate(hourlyRate));
        }

        if (serviceArea != null) {
            spec = spec.and(hasServiceArea(serviceArea));
        }

        return spec;
    }
}
