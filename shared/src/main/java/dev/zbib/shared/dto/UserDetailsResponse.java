package dev.zbib.shared.dto;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import lombok.Data;

@Data
public class UserDetailsResponse {
    private Long id;
    private String email;
    private UserRoles role;
    private AccountStatus status;
    private boolean verified;
    private boolean blocked;
} 