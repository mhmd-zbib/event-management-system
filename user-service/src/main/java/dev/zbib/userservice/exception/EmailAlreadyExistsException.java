package dev.zbib.userservice.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends UserException {
    public EmailAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Email is already taken");
    }
}
