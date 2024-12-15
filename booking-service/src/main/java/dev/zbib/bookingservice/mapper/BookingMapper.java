package dev.zbib.bookingservice.mapper;

import dev.zbib.bookingservice.dto.BookingDetailDTO;
import dev.zbib.bookingservice.dto.BookingListDTO;
import dev.zbib.bookingservice.dto.BookingResponse;
import dev.zbib.bookingservice.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .providerId(booking.getProviderId())
                .status(booking.getStatus())
                .scheduledStartTime(booking.getScheduledStartTime())
                .scheduledEndTime(booking.getScheduledEndTime())
                .serviceAddress(booking.getServiceAddress())
                .build();
    }

    public BookingDetailDTO toDetailDTO(Booking booking, String userName, String providerName) {
        return BookingDetailDTO.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .providerId(booking.getProviderId())
                .status(booking.getStatus())
                .scheduledStartTime(booking.getScheduledStartTime())
                .scheduledEndTime(booking.getScheduledEndTime())
                .actualStartTime(booking.getActualStartTime())
                .actualEndTime(booking.getActualEndTime())
                .serviceAddress(booking.getServiceAddress())
                .description(booking.getDescription())
                .totalCost(booking.getTotalCost())
                .rating(booking.getRating())
                .feedback(booking.getFeedback())
                .userName(userName)
                .providerName(providerName)
                .build();
    }

    public BookingListDTO toListDTO(Booking booking) {
        return BookingListDTO.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .providerId(booking.getProviderId())
                .status(booking.getStatus())
                .scheduledStartTime(booking.getScheduledStartTime())
                .scheduledEndTime(booking.getScheduledEndTime())
                .serviceAddress(booking.getServiceAddress())
                .build();
    }
} 