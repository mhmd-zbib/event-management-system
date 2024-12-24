package dev.zbib.bookingservice.service;


import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.shared.dto.NotificationRequest;
import dev.zbib.shared.enums.NotificationChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {


    private final NotificationPublisher notificationPublisher;

    public void sendBookingCreationNotification(Booking booking) {
        String message = "New booking creating man";
        NotificationRequest notification = NotificationRequest.builder()
                .userId(booking.getProviderId())
                .type("BookingCreation")
                .message(message)
                .channels(List.of(NotificationChannels.EMAIL, NotificationChannels.SMS))
                .metadata(buildMetadata(booking))
                .build();

        notificationPublisher.sendNotification(notification);
    }

    private Map<String, String> buildMetadata(Booking booking) {
        return Map.of(
                "bookingDate",
                booking.getBookingDate()
                        .toString(),
                "bookingStatus",
                booking.getStatus()
                        .name()
        );
    }
}

