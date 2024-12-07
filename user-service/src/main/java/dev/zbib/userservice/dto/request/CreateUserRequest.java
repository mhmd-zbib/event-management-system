package dev.zbib.userservice.dto.request;

import dev.zbib.shared.constant.ErrorMessages;
import dev.zbib.userservice.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    @NotBlank(message = ErrorMessages.User.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = ErrorMessages.User.LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = ErrorMessages.User.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = ErrorMessages.User.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank(message = ErrorMessages.User.PASSWORD_REQUIRED)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = ErrorMessages.User.INVALID_PASSWORD)
    private String password;

    @NotNull(message = ErrorMessages.User.BIRTH_DATE_REQUIRED)
    @Past(message = ErrorMessages.User.INVALID_BIRTH_DATE)
    private LocalDate birthDate;

    private String profilePicture;

    @NotNull(message = ErrorMessages.User.ADDRESS_REQUIRED)
    private Address address;

}
