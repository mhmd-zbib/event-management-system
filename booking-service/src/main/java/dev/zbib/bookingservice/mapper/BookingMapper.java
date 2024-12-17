package dev.zbib.bookingservice.mapper;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "bookingDate", expression = "java(bookingDateToLocalDateTime(req.getBookingDate()))")
    Booking toBooking(CreateBookingRequest req);

    BookingResponse toBookingResponse(Booking booking);

    default LocalDateTime bookingDateToLocalDateTime(ZonedDateTime bookingDate) {
        if (bookingDate == null) return null;
        return bookingDate.withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
    }
} 