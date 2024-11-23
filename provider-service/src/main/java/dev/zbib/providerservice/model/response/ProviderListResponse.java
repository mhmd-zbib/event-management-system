package dev.zbib.providerservice.model.response;

import dev.zbib.providerservice.model.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProviderListResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePicture;
    private ServiceType serviceType;
    private double rating;
    private boolean available;
    private double hourlyRate;
    private String serviceArea;

}
