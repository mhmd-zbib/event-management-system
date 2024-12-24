package dev.zbib.notificationservice.service;

import dev.zbib.shared.enums.NotificationChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class NotificationFactory {

    private final Map<NotificationChannels, iNotificationStrategy> notificationStrategyMap;

    public iNotificationStrategy getNotificationStrategy(NotificationChannels channel) {
        return notificationStrategyMap.get(channel);
    }
}
