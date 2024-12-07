package dev.zbib.userservice.dto.request;

import dev.zbib.shared.constant.ErrorMessages;
import dev.zbib.userservice.entity.Address;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    @NotBlank(message = ErrorMessages.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = ErrorMessages.LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = ErrorMessages.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = ErrorMessages.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank(message = ErrorMessages.PASSWORD_REQUIRED)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = ErrorMessages.INVALID_PASSWORD)
    private String password;

    @NotNull(message = ErrorMessages.BIRTH_DATE_REQUIRED)
    @Past(message = ErrorMessages.INVALID_BIRTH_DATE)
    private LocalDate birthDate;

    private String profilePicture;

    @NotNull(message = ErrorMessages.ADDRESS_REQUIRED)
    private Address address;

}
