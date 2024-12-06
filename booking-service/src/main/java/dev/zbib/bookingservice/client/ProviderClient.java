package dev.zbib.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-service")
public interface ProviderClient {
    
    @GetMapping("/providers/{providerId}/availability")
    boolean checkProviderAvailability(@PathVariable Long providerId);
} 