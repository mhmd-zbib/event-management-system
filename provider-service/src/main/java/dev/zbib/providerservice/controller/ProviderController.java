package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.dto.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.service.ProviderService;
import dev.zbib.providerservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;
    private final UserService userService;

    public ResponseEntity<ProviderResponse> createProvider(@RequestBody CreateProviderRequest request) {
        ProviderResponse res = providerService.createProvider(request);
        return ResponseEntity.ok(res);
    }
}
