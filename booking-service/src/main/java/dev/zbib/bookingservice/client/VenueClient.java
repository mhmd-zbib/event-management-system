package dev.zbib.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "venue-service", path = "/venues")
public interface VenueClient {

    @GetMapping("/{id}/booking-check")
    void checkVenueAvailability(@PathVariable String id);

}
