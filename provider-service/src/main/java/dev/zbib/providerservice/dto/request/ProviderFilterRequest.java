package dev.zbib.providerservice.dto.request;

import dev.zbib.shared.enums.ServiceType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Min(0)
    private int page = 0;

    @Min(1)
    @Max(100)
    private int size = 10;

    private String sortBy = "id";
    private String sortDirection = "asc";
    private String serviceArea;
}
