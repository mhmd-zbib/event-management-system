package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DetailsResponse {
    private Long id;
    private String bio;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
    private double rating;
    private boolean available;
}
