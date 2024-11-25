package dev.zbib.providerservice.model.response;

import dev.zbib.providerservice.model.enums.ServiceType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DetailsResponse {
    private Long id;
    private ServiceType serviceType;
    private double rating;
    private boolean available;
    private double hourlyRate;
    private String serviceArea;
}
