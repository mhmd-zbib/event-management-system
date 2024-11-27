package dev.zbib.providerservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
