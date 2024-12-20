package dev.zbib.userservice.dto.response;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;
import dev.zbib.userservice.entity.Address;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        Address address,
        UserRole role,
        AccountStatus accountStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
