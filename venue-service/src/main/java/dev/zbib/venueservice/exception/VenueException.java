package dev.zbib.venueservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VenueException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String code;

    public VenueException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
