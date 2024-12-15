package dev.zbib.providerservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProviderEligibilityResponse {
    private boolean eligible;
    private List<String> reasons;
}
