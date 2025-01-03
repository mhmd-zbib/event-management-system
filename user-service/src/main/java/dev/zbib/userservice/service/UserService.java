package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final Keycloak keycloak;
    private final ProfileService profileService;
    @Value("${keycloak.realm}")
    private String realm;

    public void createUser(RegisterRequest req) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(true);

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(req.getPassword());
        user.setCredentials(Collections.singletonList(cred));

        getUserResource().create(user);
        String userId = getUserIdByUsername(req.getUsername());
        profileService.createProfile(userId, req);
        log.info("Successfully created user: {}", req.getUsername());
    }

    public UserResource getUserById(String id) {
        return getUserResource().get(id);
    }

    private String getUserIdByUsername(String username) {
        List<UserRepresentation> users = getUserResource().search(username);
        if (users.isEmpty()) {
            log.error("No user found with username: {}", username);
            return "default-id";
        }
        String id = users.get(0).getId();
        log.info("Found user with id: {}", id);
        return id;
    }


    private UsersResource getUserResource() {
        return keycloak.realm(realm).users();
    }
}
