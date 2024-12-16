package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

public class BookingNotFoundException extends BookingException {
    public BookingNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Booking with id " + id + " not found");
    }
} 