package dev.zbib.providerservice.model.request;

import dev.zbib.providerservice.model.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterProviderRequest {
    private String bio;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
}
