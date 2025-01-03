package dev.zbib.profileservice.dto.request;

import dev.zbib.shared.entity.Address;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateProfileDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profilePicture;
    private Address address;
}
