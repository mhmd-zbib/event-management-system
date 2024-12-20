package dev.zbib.userservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends UserException {
    private final Long userId;

    public UserNotFoundException(
            Long userId) {
        super(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        this.userId = userId;
    }
}
