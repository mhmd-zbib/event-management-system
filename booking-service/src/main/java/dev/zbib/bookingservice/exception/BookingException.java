package dev.zbib.bookingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class BookingException extends RuntimeException {
    private final HttpStatus status;
    private List<String> details;

    public BookingException(
            HttpStatus status,
            String message) {
        super(message);
        this.status = status;
    }

    public BookingException(
            HttpStatus status,
            String message,
            List<String> details) {
        super(message);
        this.status = status;
        this.details = details;
    }
}
