package dev.zbib.providerservice.mapper;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.request.ProviderRequest;

public class ProviderMapper {
    public Provider toProvider(ProviderRequest request) {
        return Provider.builder()
                .userId(request.getUserId())
                .bio(request.getBio())
                .hourlyRate(request.getHourlyRate())
                .serviceArea(request.getServiceArea())
                .serviceType(request.getServiceType())
                .availableHours(request.getAvailableHours())
                .build();
    }
}
