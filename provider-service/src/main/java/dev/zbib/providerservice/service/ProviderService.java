package dev.zbib.providerservice.service;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.RegisterProviderRequest;
import dev.zbib.providerservice.model.response.DetailsListResponse;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.UserListResponse;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.providerservice.specification.ProviderSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.zbib.providerservice.model.mapper.ProviderMapper.toProvider;
import static dev.zbib.providerservice.model.mapper.ProviderMapper.toProviderListResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserClient userClient;

    public void registerProvider(
            Long id,
            RegisterProviderRequest request) {
        Provider provider = toProvider(id, request);
        providerRepository.save(provider);
    }

    public Provider getProviderById(Long userId) {
        return providerRepository.findById(userId)
                .orElse(null);
    }

    public void deleteProviderByUserId(Long userId) {
        Provider provider = getProviderById(userId);
        providerRepository.delete(provider);
    }

    public Page<ProviderListResponse> getProviders(
            ServiceType serviceType,
            Boolean available,
            Double hourlyRate,
            String serviceArea,
            Pageable pageable) {

        Specification<Provider> specification = ProviderSpecification.createFilter(
                serviceType,
                available,
                hourlyRate,
                serviceArea);

        Page<Provider> providerPage = providerRepository.findAll(specification, pageable);
        List<Long> userIds = providerPage.getContent()
                .stream()
                .map(Provider::getId)
                .collect(Collectors.toList());

        List<UserListResponse> userList = userClient.getUsersByIds(userIds);
        Map<Long, UserListResponse> userMap = userList.stream()
                .collect(Collectors.toMap(UserListResponse::getId, user -> user));

        List<ProviderListResponse> providerList = providerPage.getContent()
                .stream()
                .map(provider -> {
                    UserListResponse user = userMap.get(provider.getId());
                    return toProviderListResponse(provider, user);
                })
                .collect(Collectors.toList());
        return new PageImpl<>(providerList, pageable, providerPage.getTotalElements());
    }

    public List<DetailsListResponse> getDetailListById(List<Long> id) {
        List<Provider> providerList = providerRepository.findProvidersByIdIn(id);
        return providerList.stream()
                .map(user -> DetailsListResponse.builder()
                        .id(user.getId())
                        .available(user.isAvailable())
                        .hourlyRate(user.getHourlyRate())
                        .serviceArea(user.getServiceArea())
                        .serviceType(user.getServiceType())
                        .build())
                .collect(Collectors.toList());
    }
}
