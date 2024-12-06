package dev.zbib.bookingservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserClient {
    private final WebClient.Builder webClientBuilder;
    private static final String USER_SERVICE_URL = "http://user-service";

    public UserDetailsResponse getUserDetails(Long userId) {
        return webClientBuilder.build()
                .get()
                .uri(USER_SERVICE_URL + "/users/{id}", userId)
                .retrieve()
                .bodyToMono(UserDetailsResponse.class)
                .block();
    }

    public void validateUser(Long userId) {
        webClientBuilder.build()
                .get()
                .uri(USER_SERVICE_URL + "/users/{id}/status", userId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}