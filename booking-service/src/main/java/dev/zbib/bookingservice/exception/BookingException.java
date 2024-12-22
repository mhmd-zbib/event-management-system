package dev.zbib.bookingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BookingException extends RuntimeException {


    private HttpStatus errorCode;
    private LocalDateTime timestamp;
    private List<String> details;


    public BookingException(
            HttpStatus errorCode,
            String message,
            List<String> details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    public BookingException(
            HttpStatus errorCode,
            String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
