package dev.zbib.userservice.model.response;

import dev.zbib.userservice.model.entity.Address;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
public class UserResponse extends UserListResponse {
    private LocalDate birthDate;
    private Address address;
    private String phoneNumber;
}
