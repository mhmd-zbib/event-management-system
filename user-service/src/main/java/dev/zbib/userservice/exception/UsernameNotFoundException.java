package dev.zbib.userservice.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends UserException {
    public UsernameNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, "User " + username + " not found");
    }
}
