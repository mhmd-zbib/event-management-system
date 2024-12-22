package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

import java.util.List;


public class ProviderCantBeBookedException extends BookingException {
    public ProviderCantBeBookedException(List<String> reasons) {
        super(HttpStatus.FORBIDDEN, "You are not able to create a booking at this moment", reasons);
    }
}
