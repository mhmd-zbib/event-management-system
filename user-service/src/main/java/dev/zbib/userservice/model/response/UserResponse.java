package dev.zbib.userservice.model.response;

import dev.zbib.userservice.model.entity.Address;
import dev.zbib.userservice.model.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profilePicture;
    private Address address;
    private UserRoles role;
}
