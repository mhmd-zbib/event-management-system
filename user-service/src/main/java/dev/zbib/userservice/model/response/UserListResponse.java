package dev.zbib.userservice.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserListResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
