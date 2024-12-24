package dev.zbib.notificationservice.config;

import dev.zbib.notificationservice.service.iNotificationStrategy;
import dev.zbib.shared.enums.NotificationChannels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j // Lombok annotation for logging
public class NotificationConfig {

    @Bean
    public Map<NotificationChannels, iNotificationStrategy> notificationStrategyMap(List<iNotificationStrategy> strategies) {
        // Log the start of the map creation
        log.info("Creating notification strategy map...");

        // Use the stream to collect strategies into a map
        Map<NotificationChannels, iNotificationStrategy> strategyMap = strategies.stream()
                .peek(strategy -> {
                    // Log each strategy being processed
                    log.debug(
                            "Processing strategy: {} for channel: {}",
                            strategy.getClass()
                                    .getSimpleName(),
                            strategy.getChannelName());
                })
                .collect(Collectors.toMap(
                        iNotificationStrategy::getChannelName, // Channel
                        strategy -> strategy  // Strategy itself
                ));

        // Log the final map content
        log.info("Notification strategy map created with the following entries:");
        strategyMap.forEach((channel, strategy) -> {
            log.info(
                    "Channel: {}, Strategy: {}",
                    channel,
                    strategy.getClass()
                            .getSimpleName());
        });

        return strategyMap;
    }
}
