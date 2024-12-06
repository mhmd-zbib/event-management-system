package dev.zbib.providerservice.model.response;

import dev.zbib.shared.enums.ServiceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailsListResponse {
    private Long id;
    private ServiceType serviceType;
    private double rating;
    private boolean available;
    private double hourlyRate;
    private String serviceArea;
}
