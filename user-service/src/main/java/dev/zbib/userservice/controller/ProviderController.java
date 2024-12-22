package dev.zbib.userservice.controller;

import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.userservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/can-be-provider")
    public ResponseEntity<EligibilityResponse> canBecomeProvider(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.canBecomeProvider(id));
    }
}
