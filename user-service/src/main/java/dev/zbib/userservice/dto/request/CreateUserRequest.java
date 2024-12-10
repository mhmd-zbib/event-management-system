package dev.zbib.userservice.dto.request;

import dev.zbib.userservice.entity.Address;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private LocalDate birthDate;
    private String profilePicture;
    private Address address;

}
