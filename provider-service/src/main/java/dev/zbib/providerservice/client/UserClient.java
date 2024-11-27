package dev.zbib.providerservice.client;

import dev.zbib.providerservice.model.response.UserClientResponse;
import dev.zbib.providerservice.model.response.UserListClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    public List<UserListClientResponse> getUserListClientResponseByIdList(List<Long> ids) {
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
                .bodyToMono(new ParameterizedTypeReference<List<UserListClientResponse>>() {
                })
                .block();
    }

    public UserClientResponse getUserClientResponseById(Long id) {
        return webClientBuilder.baseUrl("http://user-service")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/users/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(UserClientResponse.class)
                .block();
    }

}
