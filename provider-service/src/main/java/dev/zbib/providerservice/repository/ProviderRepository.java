package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long>, JpaSpecificationExecutor<Provider> {

    Optional<Provider> findById(Long userId);

    List<Provider> findProvidersByIdIn(List<Long> id);
}
