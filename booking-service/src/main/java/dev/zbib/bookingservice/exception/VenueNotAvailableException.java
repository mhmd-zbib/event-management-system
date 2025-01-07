package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

public class VenueNotAvailableException extends BookingException {
    public VenueNotAvailableException() {
        super(HttpStatus.FORBIDDEN, "Venue is unavailable for booking at the momment");
    }
}
