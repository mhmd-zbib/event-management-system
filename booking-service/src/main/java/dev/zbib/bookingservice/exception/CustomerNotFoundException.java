package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;


public class CustomerNotFoundException extends BookingException {
    public CustomerNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND , "Customer with id " + id + " not found");
    }
}
