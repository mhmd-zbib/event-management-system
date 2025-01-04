package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.RegisterRequest;
import dev.zbib.userservice.exception.ServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final KeycloakService keycloakService;
    private final ProfileService profileService;

    public void createUser(RegisterRequest request) {
        UserRepresentation user = createUserRepresentation(request);
        keycloakService.createUser(user);
        String userId = keycloakService.getUserIdByUsername(request.getEmail());
        try {
            profileService.createProfile(userId, request);
        } catch (Exception e) {
            log.error("Profile creation failed for userId: {}. Rolling back user creation", userId);
            rollBackUserCreation(userId);
            throw new ServerException();
        }
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

    public void rollBackUserCreation(String id) {
        try {
            keycloakService.deleteUser(id);
            log.info("Rolled back successfully for userId: {}", id);
        } catch (Exception e) {
            log.error("Error while rolling back user creation for userId: {}", id, e);
        }
    }
}
