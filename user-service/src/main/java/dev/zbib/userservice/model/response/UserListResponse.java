package dev.zbib.userservice.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListResponse {

    private Long id;

    private String firstName;
    private String lastName;

    private String phoneNumber;

    private String profilePicture;

}
