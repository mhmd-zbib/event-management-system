package dev.zbib.authservice.service;

import dev.zbib.authservice.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {


    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public void createUser(RegisterRequest req) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setEnabled(true);

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(req.getPassword());
        user.setCredentials(Collections.singletonList(cred));

        getUserResource().create(user);
    }

    public UserResource getUser(String id) {
        return getUserResource().get(id);
    }

    private UsersResource getUserResource() {
        return keycloak.realm(realm).users();
    }
}
