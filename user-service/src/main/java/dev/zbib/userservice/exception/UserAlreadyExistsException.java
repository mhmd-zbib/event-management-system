package dev.zbib.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends UserException {
    public UserAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "Email is already taken");
    }
}
