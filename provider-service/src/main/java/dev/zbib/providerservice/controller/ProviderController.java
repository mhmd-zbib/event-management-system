package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.ProviderRequest;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
@Log4j2
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<String> registerProvider(@RequestBody ProviderRequest providerRequest) {
        providerService.registerProvider(providerRequest);
        return ResponseEntity.ok("Provider registered");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Provider> getProviderByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(providerService.getProviderByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<Page<ProviderListResponse>> getAllProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @RequestParam(required = false) ServiceType serviceType,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) Double hourlyRate,
            @RequestParam(required = false) String serviceArea) {
        Sort sort = ascending ? Sort.by(sortBy)
                .ascending() : Sort.by(sortBy)
                .descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProviderListResponse> providers = providerService.getProviders(
                serviceType,
                available,
                hourlyRate,
                serviceArea,
                pageable);
        return ResponseEntity.ok(providers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProviderByUserId(@PathVariable Long id) {
        providerService.deleteProviderByUserId(id);
        return ResponseEntity.ok("Provider deleted");
    }
}
