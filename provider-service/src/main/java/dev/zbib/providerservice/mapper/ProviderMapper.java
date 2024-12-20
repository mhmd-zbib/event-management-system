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

    Provider toProvider(CreateProviderRequest req);

    DetailsResponse toDetailsResponse(Provider provider);

    @Mapping(source = "user.id", target = "id")
    ProviderResponse toProviderResponse(
            UserResponse user,
            DetailsResponse details);
}
