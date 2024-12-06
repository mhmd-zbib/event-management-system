package dev.zbib.shared.dto;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserStatusResponse {
    private Long id;
    private UserRoles role;
    private AccountStatus status;
    private boolean isVerified;
    private boolean isBlocked;
}
