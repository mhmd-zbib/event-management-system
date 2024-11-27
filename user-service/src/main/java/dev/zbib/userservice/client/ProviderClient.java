package dev.zbib.userservice.client;

import dev.zbib.userservice.model.request.RegisterProviderRequest;
import dev.zbib.userservice.model.response.ProviderDetailsListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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

    public List<ProviderDetailsListResponse> getProviderDetailsListById(List<Long> ids) {
        return webClientBuilder.baseUrl("http://provider-service")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/providers/details")
                        .queryParam("ids", String.join(
                                ",",
                                ids.stream()
                                        .map(String::valueOf)
                                        .toArray(String[]::new)))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProviderDetailsListResponse>>() {
                })
                .block();
    }

}
