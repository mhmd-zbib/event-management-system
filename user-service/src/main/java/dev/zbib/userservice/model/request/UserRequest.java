package dev.zbib.userservice.model.request;

import dev.zbib.userservice.model.entity.Address;
import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
public class UserRequest {
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
    private LocalDate birthDate;

    private String profilePicture;
    private String history;
    private Address address;
}
