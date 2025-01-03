package dev.zbib.profileservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-service")
public interface ProviderClient {

    @GetMapping("/api/v1/providers/{providerId}/verify")
    void verifyProviderExists(@PathVariable Long providerId);
}
