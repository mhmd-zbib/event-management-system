package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;


    @PostMapping
    public ResponseEntity<String> createProvider(@RequestBody CreateProviderRequest provider) {
        providerService.createProvider(provider);
        return ResponseEntity.ok()
                .body("Provider created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getProviderBId(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

}
