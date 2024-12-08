package dev.zbib.userservice.dto.request;

import dev.zbib.userservice.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    @NotBlank(message = BaseException.User.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = BaseException.User.LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = BaseException.User.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = BaseException.User.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank(message = BaseException.User.PASSWORD_REQUIRED)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = BaseException.User.INVALID_PASSWORD)
    private String password;

    @NotNull(message = BaseException.User.BIRTH_DATE_REQUIRED)
    @Past(message = BaseException.User.INVALID_BIRTH_DATE)
    private LocalDate birthDate;

    private String profilePicture;

    @NotNull(message = BaseException.User.ADDRESS_REQUIRED)
    private Address address;

}
