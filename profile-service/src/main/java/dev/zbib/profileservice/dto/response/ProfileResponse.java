package dev.zbib.profileservice.dto.response;

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
public class ProfileResponse {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String profilePicture;
    private Address address;
}