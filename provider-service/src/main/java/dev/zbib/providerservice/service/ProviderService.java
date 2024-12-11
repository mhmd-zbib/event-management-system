package dev.zbib.providerservice.service;

import dev.zbib.providerservice.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;
}
