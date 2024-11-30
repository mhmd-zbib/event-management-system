package dev.zbib.userservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderListResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String serviceType;
    private double rating;
    private boolean available;
    private double hourlyRate;
    private String serviceArea;
}
