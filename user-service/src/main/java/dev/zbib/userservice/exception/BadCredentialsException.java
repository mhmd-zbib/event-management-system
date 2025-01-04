package dev.zbib.userservice.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends UserException {
    public BadCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }
}
