package dev.zbib.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends BaseException {
    private final HttpStatus status;

    public ServiceException(String message, String code, HttpStatus status) {
        super(message, code);
        this.status = status;
    }

    public ServiceException(String message, HttpStatus status) {
        super(message, status.name());
        this.status = status;
    }
} 