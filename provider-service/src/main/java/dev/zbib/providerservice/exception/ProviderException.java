package dev.zbib.providerservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ProviderException extends RuntimeException {
    private final HttpStatus statusCode;
    private List<String> details;

    public ProviderException(
            HttpStatus statusCode,
            String message,
            List<String> details
    ) {
        super(message);
        this.statusCode = statusCode;
        this.details = details;
    }


    public ProviderException(
            HttpStatus statusCode,
            String message
    ) {
        super(message);
        this.statusCode = statusCode;
    }
}
