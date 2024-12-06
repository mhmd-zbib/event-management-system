package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.entity.Provider;
import dev.zbib.shared.enums.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT p FROM Provider p WHERE " +
            "(:serviceType IS NULL OR p.serviceType = :serviceType) AND " +
            "(:serviceArea IS NULL OR p.serviceArea LIKE %:serviceArea%) AND " +
            "(:minHourlyRate IS NULL OR p.hourlyRate >= :minHourlyRate) AND " +
            "(:maxHourlyRate IS NULL OR p.hourlyRate <= :maxHourlyRate) AND " +
            "(:minRating IS NULL OR p.rating >= :minRating) AND " +
            "(:available IS NULL OR p.available = :available)")
    Page<Provider> findProvidersWithFilters(
            @Param("serviceType") ServiceType serviceType,
            @Param("serviceArea") String serviceArea,
            @Param("minHourlyRate") Double minHourlyRate,
            @Param("maxHourlyRate") Double maxHourlyRate,
            @Param("minRating") Double minRating,
            @Param("available") Boolean available,
            Pageable pageable
    );

    @Query("SELECT p FROM Provider p WHERE p.available = true AND p.id IN :ids")
    List<Provider> findAvailableProvidersByIds(@Param("ids") List<Long> ids);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p " +
            "WHERE p.id = :providerId AND p.available = true")
    boolean isProviderAvailable(@Param("providerId") Long providerId);
}
