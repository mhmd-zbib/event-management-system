package dev.zbib.providerservice.model.mapper;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.request.RegisterProviderRequest;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.ProviderResponse;
import dev.zbib.providerservice.model.response.UserClientResponse;
import dev.zbib.providerservice.model.response.UserListClientResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProviderMapper {


    public static Provider toProvider(

            RegisterProviderRequest request) {
        return Provider.builder()
                .id(request.getId())
                .bio(request.getBio())
                .serviceType(request.getServiceType())
                .hourlyRate(request.getHourlyRate())
                .serviceArea(request.getServiceArea())
                .build();
    }

    public static List<ProviderListResponse> toProviderListResponse(
            List<Provider> providers,
            List<UserListClientResponse> users) {
        if (providers == null || users == null || providers.size() != users.size()) {
            throw new IllegalArgumentException("Provider and User lists must have the same size and cannot be null.");
        }
        return providers.stream()
                .map(provider -> {
                    UserListClientResponse user = users.get(providers.indexOf(provider));
                    return ProviderListResponse.builder()
                            .id(provider.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .profilePicture(user.getProfilePicture())
                            .serviceType(provider.getServiceType())
                            .rating(provider.getRating())
                            .available(provider.isAvailable())
                            .hourlyRate(provider.getHourlyRate())
                            .serviceArea(provider.getServiceArea())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public static ProviderResponse toProviderResponse(
            Provider provider,
            UserClientResponse user
    ) {
        return ProviderResponse.builder()
                .id(provider.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .serviceArea(provider.getServiceArea())
                .rating(provider.getRating())
                .hourlyRate(provider.getHourlyRate())
                .serviceType(provider.getServiceType())
                .available(provider.isAvailable())
                .profilePicture(user.getProfilePicture())
                .phoneNumber(user.getPhoneNumber())
                .profilePicture(user.getProfilePicture())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .build();
    }
}
