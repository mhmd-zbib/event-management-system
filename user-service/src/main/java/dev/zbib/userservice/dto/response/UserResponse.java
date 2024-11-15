package dev.zbib.userservice.dto;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profilePicture;
    private String address;
}
