package dev.zbib.providerservice.client;

import dev.zbib.providerservice.enums.UserRoles;
import dev.zbib.shared.dto.UserResponse;
import dev.zbib.shared.dto.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    public List<UserListResponse> getUserListClientResponseByIdList(List<Long> ids) {
        return webClientBuilder.baseUrl("http://user-service")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/users")
                        .queryParam("ids", String.join(
                                ",",
                                ids.stream()
                                        .map(String::valueOf)
                                        .toArray(String[]::new)))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserListResponse>>() {
                })
                .block();
    }

    public UserResponse getUserClientResponseById(Long id) {
        return webClientBuilder.baseUrl("http://user-service")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/users/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }

    public void setUserRole(
            Long id,
            UserRoles role) {
        webClientBuilder.baseUrl("http://user-service")
                .build()
                .put()
                .uri(uri -> uri.path("/users/roles/{id}")
                        .build(id))
                .bodyValue(Collections.singletonMap("role", role))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
