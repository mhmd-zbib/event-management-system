package dev.zbib.userservice.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends UserException {
    private final Long userId;

    public UserNotFoundException(
            Long userId) {
        super("User with id " + userId + " not found");
        this.userId = userId;
    }
}
