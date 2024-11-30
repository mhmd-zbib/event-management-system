package dev.zbib.bookingservice.model.request;

import java.time.Instant;

public class CreateBookingRequest {

    private Long userId;
    private Long providerId;
    private Long serviceId;
    private Instant date;

}
