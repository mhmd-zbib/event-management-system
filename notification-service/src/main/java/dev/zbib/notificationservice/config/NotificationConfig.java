package dev.zbib.notificationservice.config;

import dev.zbib.notificationservice.service.iNotificationStrategy;
import dev.zbib.shared.enums.NotificationChannels;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class NotificationConfig {

    public Map<NotificationChannels, iNotificationStrategy> notificationStrategyMap(List<iNotificationStrategy> strategies) {
        return strategies.stream()
                .collect(Collectors.toMap(
                        iNotificationStrategy::getChannelName,
                        strategy -> strategy
                ));
    }
}
