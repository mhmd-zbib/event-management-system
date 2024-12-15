package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {


    Optional<DetailsResponse> findDetailsById(Long id);

}
