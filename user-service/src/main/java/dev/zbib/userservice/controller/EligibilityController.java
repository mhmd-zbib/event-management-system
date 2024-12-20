package dev.zbib.userservice.controller;

import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.userservice.service.EligibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/eligibility")
@RequiredArgsConstructor
public class EligibilityController {

    private final EligibilityService eligibilityService;

    @GetMapping("/can-book")
    public ResponseEntity<EligibilityResponse> canCustomerBook(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(eligibilityService.canCustomerBook(id));
    }

    @GetMapping("/can-be-booked")
    public ResponseEntity<EligibilityResponse> canProviderBeBooked(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(eligibilityService.canProviderBeBooked(id));
    }

    @GetMapping("/can-be-provider")
    public ResponseEntity<EligibilityResponse> canBecomeProvider(@PathVariable Long id) {
        return ResponseEntity.ok(eligibilityService.canBecomeProvider(id));
    }

}
