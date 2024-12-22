package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.dto.internal.ProviderValidationDTO;
import dev.zbib.providerservice.exception.ProviderNotEligibleException;
import dev.zbib.providerservice.exception.ProviderNotFoundException;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderEligibilityService {

    private final ProviderRepository providerRepository;
    private final UserClient userClient;


    public EligibilityResponse validateProviderBooking(
            Long id,
            ServiceType serviceType) {

        List<String> reasons = new ArrayList<>();
        ProviderValidationDTO provider = providerRepository.findValidationDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));

        if (!serviceType.equals(provider.serviceType())) reasons.add("Provider service type mismatch");
        if (!provider.available()) reasons.add("Provider is not available");

        return EligibilityResponse.builder()
                .reasons(reasons)
                .eligible(!reasons.isEmpty())
                .build();
    }

}
