package dev.zbib.userservice.client;

import dev.zbib.shared.dto.ProviderDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "provider-service")
public interface ProviderClient {
    @GetMapping("/api/v1/providers/batch")
    List<ProviderDetailsResponse> getProviderDetailsBatch(@RequestParam List<Long> providerIds);
    
    @GetMapping("/api/v1/providers/{providerId}/verify")
    void verifyProviderExists(@PathVariable Long providerId);
}
