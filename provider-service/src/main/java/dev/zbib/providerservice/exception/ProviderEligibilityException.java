package dev.zbib.providerservice.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ProviderEligibilityException extends ProviderException {

    private List<String> reasons;

    public ProviderEligibilityException(
            String reasons) {
        super("You aren't eligible to become a provider for the following reasons:", HttpStatus.FORBIDDEN);
    }

    public List<String> getReasons() {
        return reasons;
    }
}
