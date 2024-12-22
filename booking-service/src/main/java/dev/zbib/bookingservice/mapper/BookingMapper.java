package dev.zbib.bookingservice.mapper;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.shared.enums.BookingStatus;

public class BookingMapper {

    public static Booking toBooking(CreateBookingRequest req) {
        return Booking.builder()
                .customerId(req.getCustomerId())
                .providerId(req.getProviderId())
                .serviceType(req.getServiceType())
                .status(BookingStatus.PENDING)
                .bookingDate(req.getBookingDate())
                .build();
    }
} 