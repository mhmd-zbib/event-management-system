package dev.zbib.providerservice.dto.internal;

import dev.zbib.shared.enums.ServiceType;

public record ProviderValidationDTO(
        ServiceType serviceType,
        boolean available
) {
}
