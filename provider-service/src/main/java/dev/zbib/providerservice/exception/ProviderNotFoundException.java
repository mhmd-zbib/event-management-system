package dev.zbib.providerservice.exception;

import dev.zbib.shared.dto.AppException;
import org.springframework.http.HttpStatus;

public class ProviderNotFoundException extends AppException {
    public ProviderNotFoundException() {
        super(ExceptionMessages.PROVIDER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
