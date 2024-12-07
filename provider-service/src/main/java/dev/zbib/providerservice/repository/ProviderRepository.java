package dev.zbib.providerservice.repository;

import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {


    @Query("SELECT new dev.zbib.providerservice.dto.response.DetailsResponse(p.id,p.bio,p.serviceType, p.hourlyRate, p.serviceArea, p.rating, p.available) FROM Provider p WHERE p.id = :id")
    Optional<DetailsResponse> findDetailsById(Long id);

}
