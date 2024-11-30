package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.RegisterProviderRequest;
import dev.zbib.providerservice.model.response.DetailsListResponse;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.ProviderResponse;
import dev.zbib.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
@Log4j2
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getProviderResponseById(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.getProviderResponseById(id));
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
        Page<ProviderListResponse> providers = providerService.getProviderPage(
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

    @PostMapping()
    public ResponseEntity<String> registerProvider(
            @RequestBody RegisterProviderRequest registerProviderRequest) {
        providerService.registerProvider(registerProviderRequest);
        return ResponseEntity.ok("Provider registered");
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<String> setAvailability(
            @PathVariable Long id,
            @RequestBody boolean availability) {
        providerService.setAvailability(id, availability);
        return ResponseEntity.ok("Availability set");
    }

    @GetMapping("/details")
    public ResponseEntity<List<DetailsListResponse>> getDetailListById(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(providerService.getDetailListById(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAnalytics(@PathVariable String id) {
        /**
         * TODO: creating an analysis for the user's info and return:
         * {
         *   "total_earnings": 1500.0,
         *   "completed_bookings": 20,
         *   "canceled_bookings": 3,
         *   "top_users": [
         *     { "user_id": 101, "name": "John Doe", "total_spent": 500.0 },
         *     { "user_id": 102, "name": "Jane Smith", "total_spent": 300.0 }
         *   ],
         *   "average_rating": 4.7
         * }
         */
        return ResponseEntity.ok("Soon...");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getReviews(@PathVariable String id) {
        /**
         * TODO: fetch reviews from their place:
         * [
         *   { "user_id": 101, "rating": 5, "comment": "Excellent service!" },
         *   { "user_id": 102, "rating": 4, "comment": "Good but slightly late." }
         * ]
         */
        return ResponseEntity.ok("Soon...");

    }

}
