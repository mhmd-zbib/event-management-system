package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate birthDate,
        String profilePicture,
        UserRole role,
        AccountStatus status,
        boolean isVerified,
        boolean isBlocked,
        String bio
) {
}
