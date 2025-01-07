package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

public class VenueNotFoundException extends BookingException {
    public VenueNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Venue not found!");
    }
}
