package dev.zbib.notificationservice.service;

import dev.zbib.shared.dto.NotificationRequest;
import dev.zbib.shared.enums.NotificationChannels;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SMSNotificationStrategy implements iNotificationStrategy {

    @Override
    public NotificationChannels getChannelName() {
        return NotificationChannels.SMS;
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Sending SMS notification");
    }
}
