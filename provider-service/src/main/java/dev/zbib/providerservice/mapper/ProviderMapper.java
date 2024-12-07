package dev.zbib.providerservice.mapper;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.shared.dto.UserResponse;

public class ProviderMapper {

    public static Provider toProvider(CreateProviderRequest req) {
        return Provider.builder()
                .id(req.getId())
                .serviceArea(req.getServiceArea())
                .bio(req.getBio())
                .serviceType(req.getServiceType())
                .hourlyRate(req.getHourlyRate())
                .available(false)
                .rating(0.0)
                .build();
    }

    public static ProviderResponse toProviderResponse(
            UserResponse user,
            DetailsResponse details) {
        return ProviderResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .profilePicture(user.getProfilePicture())
                .role(user.getRole())
                .status(user.getStatus())
                .isVerified(user.isVerified())
                .isBlocked(user.isBlocked())
                .bio(details.getBio())
                .serviceType(details.getServiceType())
                .hourlyRate(details.getHourlyRate())
                .serviceArea(details.getServiceArea())
                .rating(details.getRating())
                .available(details.isAvailable())
                .build();
    }

}
