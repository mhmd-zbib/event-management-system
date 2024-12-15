package dev.zbib.providerservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProviderException extends RuntimeException {
    private final HttpStatus statusCode;

    public ProviderException(
            String message,
            HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
