package dev.zbib.userservice.service;

import dev.zbib.userservice.exception.ServerException;
import dev.zbib.userservice.exception.UsernameNotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class KeycloakService {

    private static final int MAX_SEARCH_RESULTS = 1;
    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public UserResource createUser(UserRepresentation user) {
        try (Response response = getUserResource().create(user)) {
            return getUserResource().get(user.getId());
        } catch (WebApplicationException e) {
            throw new ServerException();
        }
    }

    public Optional<UserRepresentation> getUserByUsername(String username) {
        try {
            List<UserRepresentation> users = getUserResource().search(username, 0, MAX_SEARCH_RESULTS);
            return users.stream().findFirst();
        } catch (WebApplicationException e) {
            throw new ServerException();
        }
    }

    public String getUserIdByUsername(String username) {
        return getUserByUsername(username)
                .map(UserRepresentation::getId)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private UsersResource getUserResource() {
        return keycloak.realm(realm).users();
    }
}
