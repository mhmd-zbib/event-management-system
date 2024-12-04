package dev.zbib.providerservice.dto.request;

import dev.zbib.providerservice.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterProviderRequest {
    private Long id;
    private String bio;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
}
