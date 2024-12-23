package dev.zbib.shared.dto;

import dev.zbib.shared.enums.NotificationChannels;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NotificationRequest {
    private String userId;
    private String type; // e.g., "RIDE_UPDATE", "PAYMENT_CONFIRMATION"
    private String message;
    private List<NotificationChannels> channels; // e.g., ["PUSH", "EMAIL", "SMS"]
    private Map<String, String> metadata;
}
