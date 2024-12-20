package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.dto.response.ProviderListResponse;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.service.ProviderService;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<DetailsResponse> createProvider(@RequestBody CreateProviderRequest request) {
        DetailsResponse response = providerService.createProvider(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getProvider(@PathVariable Long id) {
        ProviderResponse response = providerService.getProviderById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProviderListResponse>> getProviders(
            @ModelAttribute ProviderFilterRequest providerFilterRequest,
            Pageable pageable) {
        Page<ProviderListResponse> providerDetails = providerService.getProviders(providerFilterRequest, pageable);
        return new ResponseEntity<>(providerDetails, HttpStatus.OK);
    }

    @PostMapping("/{id}/validate-booking")
    public ResponseEntity<EligibilityResponse> validateProvider(
            @PathVariable Long id,
            @RequestBody ServiceType service) {
        return ResponseEntity.ok()
                .body(providerService.validateProviderBooking(id, service));
    }
}
