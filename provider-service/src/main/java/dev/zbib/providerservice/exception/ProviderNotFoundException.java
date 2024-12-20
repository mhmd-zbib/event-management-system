package dev.zbib.providerservice.exception;

import org.springframework.http.HttpStatus;

public class ProviderNotFoundException extends ProviderException {

    private final Long providerId;

    public ProviderNotFoundException(Long providerId) {
        super(HttpStatus.NOT_FOUND, "Provider with id " + providerId + "not found");
        this.providerId = providerId;
    }
}
