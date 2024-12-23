package dev.zbib.notificationservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import dev.zbib.shared.enums.NotificationChannels;
import org.springframework.stereotype.Service;

@Service
public interface iNotificationStrategy {
    void sendNotification(
            NotificationRequest request);

    NotificationChannels getChannelName();

}
