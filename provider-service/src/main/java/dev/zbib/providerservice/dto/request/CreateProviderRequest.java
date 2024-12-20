package dev.zbib.providerservice.dto.request;

import dev.zbib.shared.enums.ServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CreateProviderRequest {
    private Long id;

    @NotBlank(message = "Bio is required and cannot be empty.")
    private String bio;

    @NotNull(message = "Service type is required.")
    private ServiceType serviceType;

    @Positive(message = "Hourly rate must be a positive value.")
    private double hourlyRate;

    @NotBlank(message = "Service area is required and cannot be empty.")
    private String serviceArea;
}
