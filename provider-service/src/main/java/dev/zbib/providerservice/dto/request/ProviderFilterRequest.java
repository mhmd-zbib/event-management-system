package dev.zbib.providerservice.dto.request;

import dev.zbib.shared.enums.ServiceType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProviderFilterRequest {
    @Min(0)
    private Double minHourlyRate;

    @Min(0)
    private Double maxHourlyRate;

    private ServiceType serviceType;

    @Min(0)
    @Max(5)
    private Double minRating;

    @Min(0)
    @Max(5)
    private Double maxRating;

    private Boolean available;

    private String serviceArea;
}
