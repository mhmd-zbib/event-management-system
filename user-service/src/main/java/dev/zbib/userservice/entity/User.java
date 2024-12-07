package dev.zbib.userservice.entity;

import dev.zbib.shared.constant.ErrorMessages;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @NotBlank(message = ErrorMessages.User.FIRST_NAME_REQUIRED)
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = ErrorMessages.User.LAST_NAME_REQUIRED)
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = ErrorMessages.User.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = ErrorMessages.User.INVALID_PHONE_NUMBER)
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank(message = ErrorMessages.User.PASSWORD_REQUIRED)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = ErrorMessages.User.INVALID_PASSWORD)
    @Column(nullable = false)
    private String password;

    @NotNull(message = ErrorMessages.User.BIRTH_DATE_REQUIRED)
    @Past(message = ErrorMessages.User.INVALID_BIRTH_DATE)
    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    @NotNull(message = ErrorMessages.User.INVALID_ROLE)
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @NotNull
    private AccountStatus status;

    private boolean isVerified;

    private boolean isBlocked;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFavorite> favorites;
}
