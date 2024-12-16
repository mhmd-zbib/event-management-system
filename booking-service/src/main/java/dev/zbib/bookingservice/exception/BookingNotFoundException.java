package dev.zbib.bookingservice.exception;

public class BookingNotFoundException extends BookingException {
    private final Long id;

    public BookingNotFoundException(Long id) {
        super("Booking with id " + id + " not found");
        this.id = null;
    }
} 