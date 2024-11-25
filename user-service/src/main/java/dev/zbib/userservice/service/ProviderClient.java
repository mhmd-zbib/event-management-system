package dev.zbib.userservice.service;

import dev.zbib.userservice.model.request.RegisterProviderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProviderClient {


    private final WebClient.Builder webClientBuilder;


    public void registerProvider(
            Long id,
            RegisterProviderRequest registerProviderRequest) {
        webClientBuilder.baseUrl("http://provider-service")
                .build()
                .post()
                .uri("/providers/{id}", id)
                .bodyValue(registerProviderRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void deleteProvider(long id) {
        webClientBuilder.baseUrl("http://provider-service")
                .build()
                .delete()
                .uri("/providers/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
