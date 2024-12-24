package dev.zbib.shared.dto;

import dev.zbib.shared.enums.NotificationChannels;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class NotificationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String type; // e.g., "RIDE_UPDATE", "PAYMENT_CONFIRMATION"
    private String message;
    private List<NotificationChannels> channels; // e.g., ["PUSH", "EMAIL", "SMS"]
    private Map<String, String> metadata;
}
