package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueNotAvailable extends VenueException {
    public VenueNotAvailable(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
