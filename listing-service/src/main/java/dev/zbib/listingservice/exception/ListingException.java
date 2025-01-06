package dev.zbib.listingservice.exception;

import org.springframework.http.HttpStatus;

public class ListingException extends RuntimeException {
    private HttpStatus httpStatus;

    public ListingException(
            HttpStatus httpStatus,
            String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
