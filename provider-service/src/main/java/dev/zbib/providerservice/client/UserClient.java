package dev.zbib.providerservice.client;

import dev.zbib.providerservice.dto.response.ProviderEligibilityResponse;
import dev.zbib.providerservice.dto.response.UserResponse;
import dev.zbib.shared.config.FeignConfig;
import dev.zbib.shared.enums.UserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable Long id);

    @PutMapping("/users/{id}")
    void setRole(
            @PathVariable Long id,
            @RequestBody UserRole role);

    @GetMapping("/users/{id}")
    ProviderEligibilityResponse getProviderEligibility(@PathVariable Long id);
}
