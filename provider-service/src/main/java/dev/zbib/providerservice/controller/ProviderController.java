package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.request.ProviderRequest;
import dev.zbib.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<String> createProvider(@RequestParam ProviderRequest providerRequest) {
        providerService.createProvider(providerRequest);
        return ResponseEntity.ok("Provider created");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Provider> getProviderByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(providerService.getProviderByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<Page<Provider>> getAllProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy)
                .ascending() : Sort.by(sortBy)
                .descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(providerService.getAllProviders(pageable));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteProviderByUserId(@PathVariable Long userId) {
        providerService.deleteProviderByUserId(userId);
        return ResponseEntity.ok("Provider deleted");
    }

}
