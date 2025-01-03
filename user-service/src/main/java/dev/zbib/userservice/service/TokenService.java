package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public TokenResponse generateAccessToken(
            String email,
            String password) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(email)
                .password(password)
                .grantType(OAuth2Constants.PASSWORD)
                .build();

        AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
        return TokenResponse.builder()
                .accessToken(tokenResponse.getToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .expiresIn(tokenResponse.getExpiresIn())
                .build();
    }

    public TokenResponse refreshAccessToken(String refreshToken) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.REFRESH_TOKEN)
                .refreshToken(refreshToken)
                .build();

        AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
        return TokenResponse.builder()
                .accessToken(tokenResponse.getToken())
                .refreshToken(tokenResponse.getRefreshToken()) // New refresh token, if Keycloak issues it
                .expiresIn(tokenResponse.getExpiresIn())
                .build();
    }

}
