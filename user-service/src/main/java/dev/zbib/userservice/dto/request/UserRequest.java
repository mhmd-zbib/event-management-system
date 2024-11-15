package dev.zbib.userservice.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

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
    private String address;
}
