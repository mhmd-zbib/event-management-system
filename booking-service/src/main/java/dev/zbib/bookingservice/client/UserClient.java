package dev.zbib.bookingservice.client;

import dev.zbib.shared.dto.EligibilityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping()
    EligibilityResponse getCustomerBookingEligibility(@RequestParam Long userId);

    @GetMapping
    EligibilityResponse getProviderBookingEligibility(@RequestParam Long userId);
}