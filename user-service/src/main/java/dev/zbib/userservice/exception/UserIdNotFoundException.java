package dev.zbib.userservice.exception;


import org.springframework.http.HttpStatus;

public class UserIdNotFoundException extends UserException {
    public UserIdNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
    }
}
