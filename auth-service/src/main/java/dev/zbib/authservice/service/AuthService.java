package dev.zbib.authservice.service;

import dev.zbib.authservice.dto.request.LoginRequest;
import dev.zbib.authservice.dto.request.RegisterRequest;
import dev.zbib.authservice.dto.response.TokenResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Keycloak keycloak;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public TokenResponse register(RegisterRequest req) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setEnabled(true);

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(req.getPassword());

        user.setCredentials(Collections.singletonList(cred));
        keycloak.realm(realm)
                .users()
                .create(user);


        LoginRequest loginRequest = LoginRequest.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .build();
        return login(loginRequest);
    }


    public TokenResponse login(LoginRequest req) {
        Keycloak loginKeycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(req.getEmail())
                .password(req.getPassword())
                .grantType(OAuth2Constants.PASSWORD)
                .build();

        AccessTokenResponse tokenManager = loginKeycloak.tokenManager()
                .getAccessToken();
        String accessToken = tokenManager.getToken();
        String refreshToken = tokenManager.getRefreshToken();
        Long expiresIn = tokenManager.getExpiresIn();
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(expiresIn)
                .build();
    }

    public void logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token == null) {
            return;
        }

        keycloak.tokenManager()
                .invalidate(token);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
                cookie.setPath("/");
            }
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return null;
        }
        return bearerToken.substring(7);
    }
}
