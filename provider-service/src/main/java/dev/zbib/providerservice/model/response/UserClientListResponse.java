package dev.zbib.providerservice.model.response;

import lombok.Data;

@Data
public class UserClientListResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePicture;
}
