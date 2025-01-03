package dev.zbib.profileservice.entity;

import dev.zbib.profileservice.exception.ExceptionMessages;
import dev.zbib.shared.entity.Address;
import dev.zbib.shared.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    @NotNull(message = ExceptionMessages.ADDRESS_REQUIRED)
    @Embedded
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
