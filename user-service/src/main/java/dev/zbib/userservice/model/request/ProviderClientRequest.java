package dev.zbib.userservice.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderClientRequest {
    private Long userId;

    @NotNull
    @Size(min = 1, max = 50)
    private String serviceType;

    @NotNull
    private double hourlyRate;

    @NotNull
    @Size(min = 2, max = 100)
    private String serviceArea;

    private String bio;

}
