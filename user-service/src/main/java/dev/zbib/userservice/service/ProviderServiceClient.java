package dev.zbib.userservice.service;

import dev.zbib.userservice.model.request.ProviderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProviderServiceClient {


    private final WebClient.Builder webClientBuilder;

    public void createProvider(ProviderRequest request) {
        webClientBuilder.baseUrl("http://provider-service")
                .build()
                .post()
                .uri("/provider")
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    return Mono.error(new Exception("Error response: " + errorBody));
                                }))
                .bodyToMono(String.class)
                .block();
    }
}
