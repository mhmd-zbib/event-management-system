package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByUserId(Long userId);

}
