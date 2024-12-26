package dev.zbib.apigateway.dto.request;

import java.time.LocalDate;

public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate birthDate;
}
