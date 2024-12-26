package dev.zbib.apigateway.service;

import dev.zbib.apigateway.dto.request.LoginRequest;
import dev.zbib.apigateway.dto.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private String authServerUrl;

    private String realm;

    private String clientId;

    private String clientSecret;

    public void register(RegistrationRequest req) {
        // call user service save user and get the stuff for them
        // call keycloak and create a jwt token
    }


    public void login(LoginRequest req) {
        // call user service and get user
        // create token
    }
}
