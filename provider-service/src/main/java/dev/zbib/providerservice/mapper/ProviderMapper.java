package dev.zbib.providerservice.mapper;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.*;
import dev.zbib.providerservice.entity.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    Provider toProvider(CreateProviderRequest req);

    DetailsResponse toDetailsResponse(Provider provider);

    ProviderResponse toProviderResponse(
            UserResponse user,
            DetailsResponse details);

    ProviderListResponse toProviderListResponse(
            UserListResponse user,
            DetailsListResponse details);

    DetailsListResponse toDetailsListResponse(Provider provider);
}
