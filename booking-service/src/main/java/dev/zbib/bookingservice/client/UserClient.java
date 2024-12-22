package dev.zbib.bookingservice.client;

import dev.zbib.shared.dto.EligibilityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/{id}/bookings/can-book")
    EligibilityResponse canBook(@PathVariable Long id);

    @GetMapping("/users/{id}/bookings/can-be-booked")
    EligibilityResponse canBeBooked(@PathVariable Long id);
}