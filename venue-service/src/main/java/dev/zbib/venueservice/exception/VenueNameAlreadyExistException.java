package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueNameAlreadyExistException extends AppException {
    public VenueNameAlreadyExistException() {
        super(HttpStatus.BAD_REQUEST, "VEN-4000", "Venue name already exists");
    }
}
