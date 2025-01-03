package dev.zbib.profileservice.entity;

import dev.zbib.shared.entity.Address;
import jakarta.persistence.*;
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

    @Embedded
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
