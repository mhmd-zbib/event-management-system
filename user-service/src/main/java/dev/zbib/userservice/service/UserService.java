package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.RegisterRequest;
import dev.zbib.userservice.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final KeycloakService keycloakService;
    private final ProfileService profileService;

    public void createUser(RegisterRequest request) {
        if (keycloakService.getUserByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserRepresentation user = createUserRepresentation(request);
        UserResource userResource = keycloakService.createUser(user);

        String userId = userResource.toRepresentation().getId();
        profileService.createProfile(userId, request);
    }

    private UserRepresentation createUserRepresentation(RegisterRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(createCredential(request.getPassword())));
        return user;
    }

    private CredentialRepresentation createCredential(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        return credential;
    }
}
