package dev.zbib.userservice.controller;

import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.userservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/can-book")
    public ResponseEntity<EligibilityResponse> canCustomerBook(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(bookingService.canBook(id));
    }

    @GetMapping("/can-be-booked")
    public ResponseEntity<EligibilityResponse> canProviderBeBooked(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(bookingService.canBeBooked(id));
    }
}
