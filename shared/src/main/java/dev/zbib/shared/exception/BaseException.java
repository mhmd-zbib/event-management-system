package dev.zbib.shared.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final String message;
    private final String code;

    public BaseException(
            String message,
            String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
} 