package dev.zbib.providerservice.model.mapper;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.response.DetailsListResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DetailsMapper {

    // Maps a list of Providers to a list of DetailsListResponse
    public static List<DetailsListResponse> toDetailsListResponse(List<Provider> providers) {
        if (providers == null) {
            return null;  // Return null if providers list is null
        }

        return providers.stream()
                .map(provider -> DetailsListResponse.builder()
                        .id(provider.getId())
                        .available(provider.isAvailable())
                        .hourlyRate(provider.getHourlyRate())
                        .serviceArea(provider.getServiceArea())
                        .serviceType(provider.getServiceType())
                        .build())
                .collect(Collectors.toList());
    }
}
