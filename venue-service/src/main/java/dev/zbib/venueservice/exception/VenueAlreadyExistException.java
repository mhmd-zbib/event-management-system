package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueAlreadyExistException extends VenueException {
    public VenueAlreadyExistException() {
        super(HttpStatus.BAD_REQUEST, "VEN-4000", "Venue with this name already exists.");
    }
}
