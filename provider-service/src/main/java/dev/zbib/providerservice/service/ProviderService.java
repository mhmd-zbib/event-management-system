package dev.zbib.providerservice.service;

import dev.zbib.providerservice.mapper.ProviderMapper;
import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.request.ProviderRequest;
import dev.zbib.providerservice.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private ProviderMapper mapper;

    public void createProvider(ProviderRequest request) {
        Provider provider = mapper.toProvider(request);
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

    public Page<Provider> getAllProviders(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

}
