package dev.zbib.shared.dto;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profilePicture;
    private UserRoles role;
    private AccountStatus status;
    private boolean isVerified;
    private boolean isBlocked;
    private Address address;

}
