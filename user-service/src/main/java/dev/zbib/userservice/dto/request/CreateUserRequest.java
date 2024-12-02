package dev.zbib.userservice.dto.request;

import dev.zbib.userservice.entity.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateUserRequest {

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @Size(min = 8)
    private String repeatPassword;

    @NotNull
    private LocalDate birthDate;

    private String profilePicture;

    private Address address;

}
