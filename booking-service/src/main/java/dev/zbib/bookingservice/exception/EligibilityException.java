package dev.zbib.bookingservice.exception;

import java.util.List;

public class EligibilityException extends BookingException {
    private final List<String> reasons;

    public EligibilityException(List<String> reasons) {
        super("You are not able to create a booking at this moment for the following reasons:");
        this.reasons = reasons;
    }
}
