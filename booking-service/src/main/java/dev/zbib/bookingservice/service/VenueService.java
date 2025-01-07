package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.VenueClient;
import dev.zbib.bookingservice.exception.VenueNotAvailableException;
import dev.zbib.bookingservice.exception.VenueNotFoundException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueClient venueClient;

    public void checkVenueAvailability(String venueId) {
        try {
            venueClient.checkVenueAvailability(venueId);
        } catch (NotFoundException e) {
            throw new VenueNotFoundException();
        } catch (ForbiddenException e) {
            throw new VenueNotAvailableException();
        }
    }
}
