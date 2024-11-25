package dev.zbib.providerservice.service;

import dev.zbib.providerservice.model.response.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    public List<UserListResponse> getUsersByIds(List<Long> ids) {
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


}
