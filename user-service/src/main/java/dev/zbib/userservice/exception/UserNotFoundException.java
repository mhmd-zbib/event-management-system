package dev.zbib.userservice.exception;

import dev.zbib.shared.dto.AppException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AppException {
    public UserNotFoundException() {
        super(ExceptionMessages.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
 