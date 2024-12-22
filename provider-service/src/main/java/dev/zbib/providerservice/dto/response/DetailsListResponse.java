package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.ServiceType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DetailsListResponse {
    private Long id;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
    private double rating;
    private boolean available;

}
