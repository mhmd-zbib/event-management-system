package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.dto.response.UserResponse;
import dev.zbib.providerservice.exception.ProviderNotEligibleException;
import dev.zbib.providerservice.exception.ProviderNotFoundException;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.UserRole;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;


    public UserResponse getUserById(Long id) {
        try {
            return userClient.getUser(id);
        } catch (FeignException.NotFound e) {
            throw new ProviderNotFoundException(id);
        }
    }


    public void assignProviderRole(Long id) {
        try {
            userClient.setRole(id, UserRole.PROVIDER);
        } catch (FeignException.NotFound e) {
            throw new ProviderNotFoundException(id);
        }
    }


    public void canBeProvider(Long id) {
        try {
            EligibilityResponse eligibilityResponse = userClient.canBeProvider(id);
            if (!eligibilityResponse.isEligible()) {
                throw new ProviderNotEligibleException(eligibilityResponse.getReasons());
            }
        } catch (FeignException.NotFound e) {
            throw new ProviderNotFoundException(id);
        }
    }
}

