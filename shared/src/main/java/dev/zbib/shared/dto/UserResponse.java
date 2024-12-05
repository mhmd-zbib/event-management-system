package dev.zbib.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private LocalDate birthDate;
    //    private Address address;
    private String phoneNumber;
}
