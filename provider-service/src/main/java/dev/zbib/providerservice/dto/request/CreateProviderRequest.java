package dev.zbib.providerservice.dto.request;

import dev.zbib.shared.enums.ServiceType;
import lombok.Getter;

@Getter
public class CreateProviderRequest {
    private Long id;
    private String bio;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
}
