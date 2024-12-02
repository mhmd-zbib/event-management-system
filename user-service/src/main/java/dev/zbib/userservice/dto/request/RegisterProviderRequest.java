package dev.zbib.userservice.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterProviderRequest {
    private String bio;
    private String serviceType;
    private double hourlyRate;
    private String serviceArea;
}
