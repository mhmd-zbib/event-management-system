package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.dto.internal.ProviderValidationDTO;
import dev.zbib.providerservice.dto.response.DetailsListResponse;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.shared.enums.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>, JpaSpecificationExecutor<Provider> {
    Optional<DetailsResponse> findDetailsById(Long id);

    Optional<ProviderValidationDTO> findValidationDetailsById(
            Long id);

    @Query("SELECT new dev.zbib.providerservice.dto.response.DetailsListResponse" +
            " (p.id, p.serviceType, p.hourlyRate, p.serviceArea, p.rating, p.available)" +
            " FROM Provider p" +
            " WHERE (:minHourlyRate IS NULL OR p.hourlyRate >= :minHourlyRate)" +
            " AND (:maxHourlyRate IS NULL OR p.hourlyRate <= :maxHourlyRate)" +
            " AND (:serviceType IS NULL OR p.serviceType= :serviceType)" +
            " AND (:minRating IS NULL OR  p.rating >= :minRating)" +
            " AND (:maxRating IS NULL OR p.rating <= :maxRating)" +
            " AND (:available IS NULL OR p.available = :available)")
    Page<DetailsListResponse> findDetailsByFilter(
            @Param("minHourlyRate") Double minHourlyRate,
            @Param("maxHourlyRate") Double maxHourlyRate,
            @Param("serviceType") ServiceType serviceType,
            @Param("minRating") Double minRating,
            @Param("maxRating") Double maxRating,
            @Param("available") Boolean available,
            Pageable pageable);

}
