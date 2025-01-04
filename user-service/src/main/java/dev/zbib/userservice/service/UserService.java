package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.RegisterRequest;
import dev.zbib.userservice.exception.EmailAlreadyExistsException;
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
        validateUserDoesNotExist(request.getEmail());
        UserRepresentation user = createUserRepresentation(request);
        keycloakService.createUser(user);
        String userId = keycloakService.getUserIdByUsername(request.getEmail());
        profileService.createProfile(userId, request);
    }

    public void validateUserDoesNotExist(String email) {
        if (keycloakService.findByUsername(email).isPresent()) {
            log.warn("Attempted to register existing user with email: {}", email);
            throw new EmailAlreadyExistsException();
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
}
