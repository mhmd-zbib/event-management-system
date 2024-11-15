package dev.zbib.providerservice.service;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.ProviderRequest;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.providerservice.specification.ProviderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static dev.zbib.providerservice.model.mapper.ProviderMapper.toProvider;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    public void createProvider(ProviderRequest request) {
        Provider provider = toProvider(request);
        providerRepository.save(provider);
    }

    public Provider getProviderByUserId(Long userId) {
        return providerRepository.findByUserId(userId)
                .orElse(null);
    }

    public void deleteProviderByUserId(Long userId) {
        Provider provider = getProviderByUserId(userId);
        providerRepository.delete(provider);
    }

    public Page<Provider> getAllProviders(
            ServiceType serviceType,
            Boolean available,
            Double hourlyRate,
            String serviceArea,
            Pageable pageable) {
        Specification<Provider> spec = ProviderSpecification.createFilter(serviceType,
                available,
                hourlyRate,
                serviceArea);
        return providerRepository.findAll(spec, pageable);
    }

}
