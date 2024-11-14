package dev.zbib.providerservice.model.request;

import dev.zbib.providerservice.model.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderRequest {

    private Long userId;
    private String bio;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
    private String availableHours;

}
