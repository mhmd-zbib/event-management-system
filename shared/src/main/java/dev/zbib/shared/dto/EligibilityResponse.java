package dev.zbib.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EligibilityResponse {
    private boolean eligible;
    private List<String> reasons;
}
