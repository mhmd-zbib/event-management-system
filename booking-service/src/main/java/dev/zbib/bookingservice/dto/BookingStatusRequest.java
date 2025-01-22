package dev.zbib.bookingservice.dto;


import dev.zbib.bookingservice.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingStatusRequest {

    private BookingStatus status;
    private String note;

}