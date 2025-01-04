package dev.zbib.userservice.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends UserException {
    public AccessDeniedException() {
        super(HttpStatus.CONFLICT, "Account is locked or disabled");
    }
}
