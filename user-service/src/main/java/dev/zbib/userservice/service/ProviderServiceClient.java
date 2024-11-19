package dev.zbib.userservice.service;

import dev.zbib.userservice.model.request.ProviderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProviderServiceClient {

    public void createProvider(ProviderRequest request) {
        log.info("Creating provider");
    }
}
