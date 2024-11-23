package dev.zbib.userservice.model.mappers;

import dev.zbib.userservice.model.request.ProviderClientRequest;

public class ProviderMapper {

    public static ProviderClientRequest toProvider(ProviderRequest providerRequest) {
        return ProviderClientRequest.builder()
                .bio(providerRequest.getBio())
                .hourlyRate(providerRequest.getHourlyRate())
                .serviceArea(providerRequest.getServiceArea())
                .serviceType(providerRequest.getServiceType())
                .build();
    }
}
