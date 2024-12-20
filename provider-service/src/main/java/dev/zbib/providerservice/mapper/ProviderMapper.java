package dev.zbib.providerservice.mapper;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.dto.response.UserResponse;
import dev.zbib.providerservice.entity.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Provider toProvider(CreateProviderRequest req);

    DetailsResponse toDetailsResponse(Provider provider);

    @Mapping(source = "userResponse.id", target = "id")
    @Mapping(source = "userResponse.firstName", target = "firstName")
    @Mapping(source = "userResponse.lastName", target = "lastName")
    @Mapping(source = "userResponse.phoneNumber", target = "phoneNumber")
    @Mapping(source = "userResponse.profilePicture", target = "profilePicture")
    @Mapping(source = "userResponse.address", target = "address")
    @Mapping(source = "userResponse.role", target = "role")
    @Mapping(source = "userResponse.accountStatus", target = "accountStatus")
    @Mapping(source = "detailsResponse.bio", target = "bio")
    @Mapping(source = "detailsResponse.serviceType", target = "serviceType")
    @Mapping(source = "detailsResponse.hourlyRate", target = "hourlyRate")
    @Mapping(source = "detailsResponse.serviceArea", target = "serviceArea")
    @Mapping(source = "detailsResponse.rating", target = "rating")
    @Mapping(source = "detailsResponse.available", target = "available")
    ProviderResponse toProviderResponse(
            UserResponse userResponse,
            DetailsResponse detailsResponse);
}
