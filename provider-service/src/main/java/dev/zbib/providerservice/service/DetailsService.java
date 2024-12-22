package dev.zbib.providerservice.service;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.dto.response.DetailsListResponse;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.providerservice.exception.ProviderNotFoundException;
import dev.zbib.providerservice.mapper.ProviderMapper;
import dev.zbib.providerservice.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailsService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;


    public DetailsResponse createDetails(CreateProviderRequest request) {
        Provider provider = providerMapper.toProvider(request);
        Provider createdProvider = providerRepository.save(provider);
        return providerMapper.toDetailsResponse(createdProvider);
        }

    public DetailsResponse getDetailsById(Long id) {
        return providerRepository.findDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));
    }

    public Page<DetailsListResponse> getDetailList(
            ProviderFilterRequest filter,
            Pageable page) {
        return providerRepository.findDetailsByFilter(
                filter.getMinHourlyRate(),
                filter.getMaxHourlyRate(),
                filter.getServiceType(),
                filter.getMinRating(),
                filter.getMaxRating(),
                filter.getAvailable(),
                page);
    }
}
