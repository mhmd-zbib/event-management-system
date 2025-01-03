package dev.zbib.profileservice.dto;

import java.time.LocalDate;

public record ProfileResponse(
        String id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String profilePicture
) {
}
