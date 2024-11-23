package dev.zbib.providerservice.model.mapper;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.request.ProviderRequest;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.UserClientListResponse;

public class ProviderMapper {
    public static Provider toProvider(ProviderRequest request) {
        return Provider.builder()
                .userId(request.getUserId())
                .bio(request.getBio())
                .hourlyRate(request.getHourlyRate())
                .serviceArea(request.getServiceArea())
                .serviceType(request.getServiceType())
                .build();
    }

    public static ProviderListResponse toResponseList(
            Provider provider,
            UserClientListResponse user) {
        return ProviderListResponse.builder()
                .id(provider.getUserId())
                .serviceType(provider.getServiceType())
                .rating(provider.getRating())
                .available(provider.isAvailable())
                .hourlyRate(provider.getHourlyRate())
                .serviceArea(provider.getServiceArea())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .profilePicture(user.getProfilePicture())
                .build();
    }

}
