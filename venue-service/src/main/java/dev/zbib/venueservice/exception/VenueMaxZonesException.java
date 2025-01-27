package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class VenueMaxZonesException extends AppException {
    public VenueMaxZonesException() {
        super(HttpStatus.BAD_REQUEST, "VEN-6410", "Maximum number of zones reached");
    }
}
