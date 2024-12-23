package dev.zbib.notificationservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import dev.zbib.shared.enums.NotificationChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationDispatcher {
    private final NotificationFactory notificationFactory;

    public void dispatch(NotificationRequest request) {
        List<NotificationChannels> channels = request.getChannels();
        for (NotificationChannels channel : channels) {
            iNotificationStrategy strategy = notificationFactory.getNotificationStrategy(channel);
            strategy.sendNotification(request);
        }
    }
}
