package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueMaxCapacityException extends VenueException {
    public VenueMaxCapacityException() {
        super(HttpStatus.BAD_REQUEST, "VEN-4001", "Capacity must be between 1 and 100000");
    }
}
