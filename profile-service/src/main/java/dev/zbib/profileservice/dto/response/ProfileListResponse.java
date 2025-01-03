package dev.zbib.profileservice.dto.response;

public record ProfileListResponse(
        Long id,
        String firstName,
        String lastName,
        String profilePicture
) {
}
