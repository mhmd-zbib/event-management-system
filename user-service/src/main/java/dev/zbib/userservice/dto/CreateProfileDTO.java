package dev.zbib.userservice.dto;

import dev.zbib.shared.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProfileDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profilePicture;
    private Address address;
}
