package dev.zbib.notificationservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationDispatcher notificationDispatcher;

    @RabbitListener(queues = {"notification.queue"})
    public void handleNotification(NotificationRequest request) {
        notificationDispatcher.dispatch(request);
    }
}
