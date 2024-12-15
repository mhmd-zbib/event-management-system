package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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