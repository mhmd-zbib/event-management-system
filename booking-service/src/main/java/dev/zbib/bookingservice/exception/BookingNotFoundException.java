package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class BookingNotFoundException extends BookingException {
    public BookingNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Booking with id " + id + " not found");
    }
} 