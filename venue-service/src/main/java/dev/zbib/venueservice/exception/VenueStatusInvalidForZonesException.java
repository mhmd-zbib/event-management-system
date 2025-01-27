package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueStatusInvalidForZonesException extends AppException {
    public VenueStatusInvalidForZonesException() {
        super(HttpStatus.BAD_REQUEST, "VEN-1249", "Venue is not able to accept zones at this moment");
    }
}
