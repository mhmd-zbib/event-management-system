package dev.zbib.userservice.entity;

import dev.zbib.userservice.exception.ExceptionMessages;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ExceptionMessages.FIRST_NAME_REQUIRED)
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = ExceptionMessages.LAST_NAME_REQUIRED)
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = ExceptionMessages.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = ExceptionMessages.INVALID_PHONE_NUMBER)
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = ExceptionMessages.PASSWORD_REQUIRED)
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = ExceptionMessages.INVALID_PASSWORD
    )
    @Column(nullable = false)
    private String password;

    @NotNull(message = ExceptionMessages.BIRTH_DATE_REQUIRED)
    @Past(message = ExceptionMessages.INVALID_BIRTH_DATE)
    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @NotNull(message = ExceptionMessages.ADDRESS_REQUIRED)
    @Embedded
    private Address address;

    @NotNull
    private AccountStatus status;

    @Column(nullable = false)
    private boolean isVerified;

    @Column(nullable = false)
    private boolean isBlocked;

}
