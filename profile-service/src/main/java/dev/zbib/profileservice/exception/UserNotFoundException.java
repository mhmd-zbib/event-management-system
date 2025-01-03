package dev.zbib.profileservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends UserException {
    private final String userId;

    public UserNotFoundException(
            String userId) {
        super(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        this.userId = userId;
    }
}
