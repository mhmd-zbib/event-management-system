package dev.zbib.bookingservice.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BookingTimeOverlapException extends BookingException {

    public BookingTimeOverlapException(LocalDateTime bookingTime) {
        super(HttpStatus.CONFLICT, "The provider is not available at " + bookingTime);
    }
}
