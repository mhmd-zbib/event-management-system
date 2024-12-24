package dev.zbib.notificationservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationDispatcher notificationDispatcher;

    @RabbitListener(queues = "booking.queue", concurrency = "5-10")
    public void handleBookingNotification(NotificationRequest request) {
        notificationDispatcher.dispatch(request);
    }

    @RabbitListener(queues = "provider.queue", concurrency = "3-5")
    public void handleProviderNotification(NotificationRequest request) {
        notificationDispatcher.dispatch(request);
    }

    @RabbitListener(queues = "user.queue", concurrency = "3-5")
    public void handleUserNotification(NotificationRequest request) {
        notificationDispatcher.dispatch(request);
    }

}
