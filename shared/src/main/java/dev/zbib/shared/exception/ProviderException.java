package dev.zbib.shared.exception;

import dev.zbib.shared.constant.ProviderExceptionMessage;
import org.springframework.http.HttpStatus;

public class ProviderException extends AppException {
    private ProviderException(
            String message,
            HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static ProviderException notFound() {
        return new ProviderException(ProviderExceptionMessage.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
