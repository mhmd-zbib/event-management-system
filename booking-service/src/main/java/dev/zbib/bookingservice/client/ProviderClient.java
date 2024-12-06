package dev.zbib.bookingservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Log4j2
public class ProviderClient {
    private final WebClient.Builder webClientBuilder;
    private static final String PROVIDER_SERVICE_URL = "http://provider-service";

    public ProviderDetailsResponse getProviderDetails(Long providerId) {
        return webClientBuilder.build()
                .get()
                .uri(PROVIDER_SERVICE_URL + "/providers/{id}", providerId)
                .retrieve()
                .bodyToMono(ProviderDetailsResponse.class)
                .block();
    }

    public boolean checkProviderAvailability(Long providerId) {
        return webClientBuilder.build()
                .get()
                .uri(PROVIDER_SERVICE_URL + "/providers/{id}/status", providerId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
} 