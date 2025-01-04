package dev.zbib.userservice.service;

import dev.zbib.userservice.exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class KeycloakService {

    private static final int MAX_SEARCH_RESULTS = 1;
    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public void createUser(UserRepresentation user) {
        getUserResource().create(user);
    }

    public Optional<UserRepresentation> findByUsername(String username) {
        return getUserResource()
                .search(username, 0, MAX_SEARCH_RESULTS)
                .stream()
                .findFirst();
    }

    public String getUserIdByUsername(String username) {
        return findByUsername(username).map(UserRepresentation::getId)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private UsersResource getUserResource() {
        return keycloak.realm(realm).users();
    }
}
