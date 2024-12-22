package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.service.BookingService;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers/{id}/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/can-be-booked")
    public ResponseEntity<EligibilityResponse> canProviderBeBooked(
            @PathVariable Long id,
            @RequestBody ServiceType serviceType) {
        return ResponseEntity.ok()
                .body(bookingService.canBeBooked(id, serviceType));
    }
}
