package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

public class ProviderNotFoundException extends BookingException {
    public ProviderNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Provider with id " + id + " not found");
    }
}
