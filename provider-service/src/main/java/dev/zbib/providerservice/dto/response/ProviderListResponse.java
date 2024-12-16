package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.ServiceType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderListResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
    private double rating;
    private boolean available;
}
