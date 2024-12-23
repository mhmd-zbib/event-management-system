package dev.zbib.bookingservice.events;

import dev.zbib.shared.enums.ServiceType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BookingCreatedEvent {

    private final Long bookingId;
    private final Long providerId;
    private final Long customerId;
    private final LocalDateTime bookingDate;
    private final ServiceType serviceType;
    private final Double amount;

}