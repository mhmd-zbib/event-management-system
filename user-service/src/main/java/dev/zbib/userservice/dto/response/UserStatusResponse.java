package dev.zbib.userservice.dto.response;

import dev.zbib.userservice.enums.AccountStatus;
import dev.zbib.userservice.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserStatusResponse {
    private Long id;
    private UserRoles role;
    private AccountStatus status;
    private boolean isVerified;
    private boolean isBlocked;
}
