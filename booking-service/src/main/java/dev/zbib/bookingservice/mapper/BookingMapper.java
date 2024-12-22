package dev.zbib.bookingservice.mapper;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toBooking(CreateBookingRequest req);

    BookingResponse toBookingResponse(Booking booking);
} 