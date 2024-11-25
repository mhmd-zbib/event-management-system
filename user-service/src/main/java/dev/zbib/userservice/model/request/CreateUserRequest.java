package dev.zbib.userservice.model.request;

import dev.zbib.userservice.model.entity.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
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
