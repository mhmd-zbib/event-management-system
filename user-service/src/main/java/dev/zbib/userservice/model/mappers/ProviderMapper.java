package dev.zbib.userservice.model.mappers;

import dev.zbib.userservice.model.request.ProviderRequest;
import dev.zbib.userservice.model.request.ProviderServiceRequest;

public class ProviderMapper {

    public static ProviderServiceRequest toProvider(ProviderRequest providerRequest) {
        return ProviderServiceRequest.builder()
                .bio(providerRequest.getBio())
                .hourlyRate(providerRequest.getHourlyRate())
                .serviceArea(providerRequest.getServiceArea())
                .serviceType(providerRequest.getServiceType())
                .build();
    }
}
