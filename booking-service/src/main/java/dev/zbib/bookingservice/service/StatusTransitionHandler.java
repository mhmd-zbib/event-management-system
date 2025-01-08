package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.enums.BookingStatus;

import java.util.Set;

public interface StatusTransitionHandler {
    Set<BookingStatus> getAllowedTransitions();
}
