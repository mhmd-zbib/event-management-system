package dev.zbib.bookingservice.builder;

import dev.zbib.bookingservice.dto.BookingListResponse;
import dev.zbib.bookingservice.dto.BookingCreationRequest;
import dev.zbib.bookingservice.dto.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;

public class BookingBuilder {

    public static Booking buildBooking(String userId, BookingCreationRequest req) {
        return Booking.builder()
                .userId(userId)
                .eventId(req.getEventId())
                .venueId(req.getVenueId())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .status(req.getStatus())
                .notes(req.getNotes())
                .build();
    }

    public static BookingResponse buildBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .eventId(booking.getEventId())
                .userId(booking.getUserId())
                .venueId(booking.getVenueId())
                .reference(booking.getReference())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .status(booking.getStatus())
                .paymentStatus(booking.getPaymentStatus())
                .totalPrice(booking.getTotalPrice())
                .notes(booking.getNotes())
                .build();
    }

    public static BookingListResponse buildBookingListResponse(Booking booking) {
        return BookingListResponse.builder()
                .id(booking.getId())
                .eventId(booking.getEventId())
                .userId(booking.getUserId())
                .venueId(booking.getVenueId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .status(booking.getStatus())
                .paymentStatus(booking.getPaymentStatus())
                .totalPrice(booking.getTotalPrice())
                .build();
    }
}