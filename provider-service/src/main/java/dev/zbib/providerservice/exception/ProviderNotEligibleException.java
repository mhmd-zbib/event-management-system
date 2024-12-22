package dev.zbib.providerservice.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ProviderNotEligibleException extends ProviderException {
    public ProviderNotEligibleException(List<String> reasons) {
        super(HttpStatus.FORBIDDEN, "You aren't eligible to become a provider for the following reasons:", reasons);
    }
}
