package dev.zbib.shared.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message, "BAD_REQUEST");
    }
} 