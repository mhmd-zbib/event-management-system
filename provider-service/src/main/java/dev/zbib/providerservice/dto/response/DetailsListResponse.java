package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.ServiceType;

public record DetailsListResponse(
        Long id,
        ServiceType serviceType,
        double hourlyRate,
        String serviceArea,
        double rating,
        boolean available
) {
}
