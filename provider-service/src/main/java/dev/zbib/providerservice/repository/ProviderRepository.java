package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.dto.internal.ProviderValidationDTO;
import dev.zbib.providerservice.dto.response.DetailsListResponse;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>, JpaSpecificationExecutor<Provider> {
    Optional<DetailsResponse> findDetailsById(Long id);

    Optional<ProviderValidationDTO> findValidationDetailsById(
            Long id);

}
