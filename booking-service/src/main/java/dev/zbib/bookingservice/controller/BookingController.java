package dev.zbib.bookingservice.controller;

import dev.zbib.bookingservice.dto.BookingListResponse;
import dev.zbib.bookingservice.dto.BookingRequest;
import dev.zbib.bookingservice.dto.BookingResponse;
import dev.zbib.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @AuthenticationPrincipal Jwt jwt, @RequestBody BookingRequest request) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(bookingService.createBooking(userId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<Page<BookingListResponse>> getBookings(Pageable pageable) {
        return ResponseEntity.ok(bookingService.getBookings(pageable));
    }
}
