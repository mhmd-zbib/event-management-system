package dev.zbib.authservice.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final AuthService authService;
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;


    public void assignRole(
            String id,
            String roleName) {
        UserResource user = authService.getUser(id);
        final RoleRepresentation representation = getRolesResource().get(roleName)
                .toRepresentation();
        user.roles()
                .realmLevel()
                .add(Collections.singletonList(representation));
    }

    public RolesResource getRolesResource() {
        return keycloak.realm(realm)
                .roles();
    }

}
