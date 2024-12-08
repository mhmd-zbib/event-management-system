package dev.zbib.shared.exception;

import dev.zbib.shared.constant.UserExceptionMessage;
import org.springframework.http.HttpStatus;

public class UserException extends AppException {
    public UserException(
            String message,
            HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static UserException notFound() {
        return new UserException(UserExceptionMessage.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
