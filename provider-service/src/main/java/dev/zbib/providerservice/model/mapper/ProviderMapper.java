package dev.zbib.providerservice.model.mapper;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.request.RegisterProviderRequest;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.UserListResponse;

public class ProviderMapper {

    public static Provider toProvider(
            Long id,
            RegisterProviderRequest request) {
        return Provider.builder()
                .id(id)
                .bio(request.getBio())
                .hourlyRate(request.getHourlyRate())
                .serviceArea(request.getServiceArea())
                .serviceType(request.getServiceType())
                .build();
    }

    public static ProviderListResponse toProviderListResponse(
            Provider provider,
            UserListResponse userListResponse) {
        return ProviderListResponse.builder()
                .id(provider.getId())
                .firstName(userListResponse.getFirstName())
                .lastName(userListResponse.getLastName())
                .profilePicture(userListResponse.getProfilePicture())
                .serviceType(provider.getServiceType())
                .rating(provider.getRating())
                .available(provider.isAvailable())
                .hourlyRate(provider.getHourlyRate())
                .serviceArea(provider.getServiceArea())
                .build();
    }


}
