package dev.zbib.providerservice.model.response;

import dev.zbib.providerservice.model.entity.Address;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private LocalDate birthDate;
    private Address address;
    private String phoneNumber;
}
