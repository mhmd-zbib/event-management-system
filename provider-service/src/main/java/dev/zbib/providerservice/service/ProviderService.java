package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.dto.response.ProviderEligibilityResponse;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.dto.response.UserResponse;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.providerservice.exception.ProviderEligibilityException;
import dev.zbib.providerservice.exception.ProviderNotFoundException;
import dev.zbib.providerservice.mapper.ProviderMapper;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.shared.enums.UserRole;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final UserClient userClient;
    private final ProviderMapper providerMapper;

    public DetailsResponse createProvider(CreateProviderRequest request) {
        Long id = request.getId();
        validateProviderEligibility(id);
        assignProviderRole(id);

        Provider provider = providerMapper.toProvider(request);
        Provider createdProvider = providerRepository.save(provider);
        return providerMapper.toDetailsResponse(createdProvider);
    }

    public ProviderResponse getProviderById(Long id) {
        DetailsResponse details = providerRepository.findDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));
        UserResponse user;
        try {
            user = userClient.getUser(id);
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
        return providerMapper.toProviderResponse(user, details);
    }

    private void validateProviderEligibility(Long id) {
        try {
            ProviderEligibilityResponse eligibilityResponse = userClient.getProviderEligibility(id);
            if (!eligibilityResponse.isEligible()) {
                throw new ProviderEligibilityException(eligibilityResponse.getReasons());
            }
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
    }

    private void assignProviderRole(Long id) {
        try {
            userClient.setRole(id, UserRole.PROVIDER);
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
    }

}
