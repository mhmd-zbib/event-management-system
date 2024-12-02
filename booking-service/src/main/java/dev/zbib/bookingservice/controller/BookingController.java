package dev.zbib.bookingservice.controller;

import dev.zbib.bookingservice.model.request.CreateDirectBookingRequest;
import dev.zbib.bookingservice.model.request.CreateOpenBookingRequest;
import org.springframework.web.bind.annotation.*;

@RestController("/booking")
public class BookingController {

    @PostMapping("/direct")
    public void createDirectBooking(@RequestBody CreateDirectBookingRequest req) {
        // call the booking service to create a booking
        // return the booking id, the status and te time for it, and put if a provider is assigned
    }

    @PostMapping("/open")
    public void createOpenBooking(@RequestBody CreateOpenBookingRequest req) {

    }

    @GetMapping
    public void getBookingStatus(@PathVariable Long id) {
        // call the service to get the details of the booking

        // returns the id, status, provider assigned, and estimated time of arrival
    }

    @PutMapping("/{id}")
    public void updateBooking(
            // @RequestBody UpdateBookingRequest req,
            @PathVariable Long id) {
        // cal the booking and update the info
        // return its info
    }

    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        //  call service for deleting
    }

}
