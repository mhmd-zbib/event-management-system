package dev.zbib.authservice.service;

import dev.zbib.authservice.dto.request.LoginRequest;
import dev.zbib.authservice.dto.request.RegisterRequest;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    public String register(RegisterRequest req) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEnabled(true);

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(req.getPassword());

        user.setCredentials(Collections.singletonList(cred));
        Response response = keycloak.realm(realm)
                .users()
                .create(user);

        return CreatedResponseUtil.getCreatedId(response);
    }


    public String login(LoginRequest req) {
        return keycloak.tokenManager()
                .getAccessToken()
                .getToken();
    }
}
