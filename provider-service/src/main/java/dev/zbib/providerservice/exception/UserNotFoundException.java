package dev.zbib.providerservice.exception;

import dev.zbib.shared.dto.AppException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AppException {
    public UserNotFoundException() {
        super(ExceptionMessages.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
