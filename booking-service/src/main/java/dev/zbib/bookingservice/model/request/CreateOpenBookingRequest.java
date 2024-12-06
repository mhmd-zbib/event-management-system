package dev.zbib.bookingservice.model.request;

import java.time.Instant;
import java.util.List;

public class CreateOpenBookingRequest {

    private Long userId;
    private Long serviceId;
    private Instant bookingDate;
    private String title;
    private String description;
    private String additionalInfo;
    private List<String> photos;

}
