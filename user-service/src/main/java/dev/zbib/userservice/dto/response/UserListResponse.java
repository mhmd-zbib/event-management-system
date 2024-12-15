package dev.zbib.userservice.dto.response;

public record UserListResponse(
        Long id,
        String firstName,
        String lastName,
        String profilePicture
) {
}
