package dev.zbib.notificationservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import dev.zbib.shared.enums.NotificationChannels;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationDispatcher {
    private final NotificationFactory notificationFactory;

    public void dispatch(NotificationRequest request) {
        List<NotificationChannels> channels = request.getChannels();
        for (NotificationChannels channel : channels) {
            iNotificationStrategy strategy = notificationFactory.getNotificationStrategy(channel);
            if (strategy != null) {
                strategy.sendNotification(request);
            } else {
                log.warn("No strategy found for channel {}", channel);
            }
        }
    }
}
