package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueNotFoundException extends VenueException {
    public VenueNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Listing with id " + id + " not found");
    }
}
