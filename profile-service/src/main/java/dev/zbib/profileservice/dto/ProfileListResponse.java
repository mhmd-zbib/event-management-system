package dev.zbib.profileservice.dto;

public record ProfileListResponse(
        Long id,
        String firstName,
        String lastName,
        String profilePicture
) {
}
