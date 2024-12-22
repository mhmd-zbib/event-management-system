package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.ServiceType;

public record DetailsResponse(
        Long id,
        String bio,
        ServiceType serviceType,
        double hourlyRate,
        String serviceArea,
        double rating,
        boolean available
) {
}