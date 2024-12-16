package dev.zbib.providerservice.dto.internal;

import dev.zbib.shared.enums.ServiceType;
import lombok.Getter;

@Getter
public record ProviderValidationDTO(
        ServiceType serviceType,
        boolean availability
) {
}
