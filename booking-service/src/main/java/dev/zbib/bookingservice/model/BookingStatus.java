package dev.zbib.bookingservice.model;

public enum BookingStatus {
    PENDING,        // Initial state when booking is created
    ACCEPTED,       // Provider has accepted the booking
    REJECTED,       // Provider has rejected the booking
    CANCELLED,      // User has cancelled the booking
    IN_PROGRESS,    // Service is currently being provided
    COMPLETED,      // Service has been completed
    NO_SHOW         // Either party didn't show up
} 