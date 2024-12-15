package dev.zbib.providerservice.exception;

import org.springframework.http.HttpStatus;

public class ProviderNotFoundException extends ProviderException {

    private final Long providerId;

    public ProviderNotFoundException(Long providerId) {
        super("Provider with id " + providerId + "not found", HttpStatus.NOT_FOUND);
        this.providerId = providerId;
    }
}
