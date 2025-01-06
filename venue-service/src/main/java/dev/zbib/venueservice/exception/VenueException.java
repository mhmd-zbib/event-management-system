package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueException extends RuntimeException {
    private HttpStatus httpStatus;

    public VenueException(
            HttpStatus httpStatus,
            String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
