package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class MaxImageCountException extends VenueException {
    public MaxImageCountException() {
        super(HttpStatus.BAD_REQUEST, "IMG-100", "Maximum of 10 images allowed per entity");
    }
}
