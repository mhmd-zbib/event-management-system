package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

import java.util.List;


public class ProviderDetailsMismatchException extends BookingException {
    public ProviderDetailsMismatchException(
            List<String> details) {
        super(HttpStatus.BAD_REQUEST, "Provider details error", details);
    }
}
