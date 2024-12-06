package dev.zbib.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserListResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
