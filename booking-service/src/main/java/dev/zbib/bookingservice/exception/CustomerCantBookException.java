package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class CustomerCantBookException extends BookingException {
    public CustomerCantBookException(List<String> reasons) {
        super(
                HttpStatus.CONFLICT,
                "You are not able to create a booking at this moment for the following reasons:",
                reasons);
    }
}
