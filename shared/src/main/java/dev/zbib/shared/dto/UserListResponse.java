package dev.zbib.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
