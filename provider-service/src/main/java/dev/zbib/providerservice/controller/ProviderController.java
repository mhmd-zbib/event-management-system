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
        ProviderResponse response = providerService.getProvider(id);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Page<ProviderListResponse>> getProviders(
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating,
            @RequestParam(value = "minHourlyRate", required = false) Double minHourlyRate,
            @RequestParam(value = "maxHourlyRate", required = false) Double maxHourlyRate,
            @RequestParam(value = "available", required = false) Boolean available,
            @RequestParam(value = "serviceArea", required = false) String serviceArea,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {

        ProviderFilterRequest filter = ProviderFilterRequest.builder()
                .minRating(minRating)
                .maxRating(maxRating)
                .minHourlyRate(minHourlyRate)
                .maxHourlyRate(maxHourlyRate)
                .available(available)
                .serviceArea(serviceArea)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();
        Page<ProviderListResponse> providerDetails = providerService.getDetails(filter);
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
