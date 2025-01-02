package dev.zbib.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProfileDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
