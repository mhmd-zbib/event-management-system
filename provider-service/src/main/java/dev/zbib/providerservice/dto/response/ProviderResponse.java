package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.entity.Address;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.ServiceType;
import dev.zbib.shared.enums.UserRole;

public record ProviderResponse(

        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String profilePicture,
        Address address,
        UserRole role,
        AccountStatus accountStatus,
        String bio,
        ServiceType serviceType,
        double hourlyRate,
        String serviceArea,
        double rating,
        boolean available
) {
}
