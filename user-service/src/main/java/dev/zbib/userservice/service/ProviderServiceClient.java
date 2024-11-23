package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.request.ProviderRequest;
import dev.zbib.userservice.model.request.ProviderServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static dev.zbib.userservice.model.mappers.ProviderMapper.toProvider;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProviderServiceClient {


    private final WebClient.Builder webClientBuilder;
    private final UserService userService;


    public void createProvider(ProviderRequest providerRequest) {
        User user = userService.createUser(providerRequest);
        ProviderServiceRequest providerServiceRequest = toProvider(providerRequest);
        providerServiceRequest.setUserId(user.getId());
        webClientBuilder.baseUrl("http://provider-service")
                .build()
                .post()
                .uri("/provider")
                .bodyValue(providerServiceRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
