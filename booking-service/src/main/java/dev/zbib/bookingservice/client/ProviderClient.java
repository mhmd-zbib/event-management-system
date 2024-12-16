package dev.zbib.bookingservice.client;

import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "provider-service")
public interface ProviderClient {

    @GetMapping("/providers/{providerId}/availability")
    EligibilityResponse getProviderAvailability(
            @PathVariable Long providerId,
            @RequestBody ServiceType serviceType
    );
}