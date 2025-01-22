package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.VenueClient;
import dev.zbib.bookingservice.exception.VenueNotAvailableException;
import dev.zbib.bookingservice.exception.VenueNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueClient venueClient;

    public void checkVenueAvailability(String venueId) {
        log.info("ID IS {}", venueId);
        try {
            venueClient.checkVenueAvailability(venueId);
        } catch (FeignException.NotFound e) {
            throw new VenueNotFoundException();
        } catch (FeignException.Forbidden e) {
            throw new VenueNotAvailableException();
        }
    }
}
