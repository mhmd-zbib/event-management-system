package dev.zbib.bookingservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(NotificationRequest notification) {
        String exchange = "notification.exchange";
        String routingKey = "notification.key";
        rabbitTemplate.convertAndSend(exchange, routingKey, notification);
        log.info("Notification sent to " + notification.getUserId());
    }
}
