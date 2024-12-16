package dev.zbib.bookingservice.exception;

import java.util.List;

public class ProviderDetailsException extends BookingException {
    private final List<String> details;

    public ProviderDetailsException(
            List<String> details) {
        super("Provider details error");
        this.details = details;
    }
}
