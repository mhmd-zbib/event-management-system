package dev.zbib.bookingservice.client;

import dev.zbib.bookingservice.integration.UserStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    public UserStatusResponse getUserStatus(Long id) {
        return webClientBuilder.baseUrl("http://user-service")
                .build()
                .get()
                .uri("/users/{id}/status", id)
                .retrieve()
                .bodyToMono(UserStatusResponse.class)
                .block();
    }
}
