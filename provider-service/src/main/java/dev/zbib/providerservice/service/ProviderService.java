package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.RegisterProviderRequest;
import dev.zbib.providerservice.model.response.*;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.providerservice.specification.ProviderSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.providerservice.model.mapper.DetailsMapper.toDetailsListResponse;
import static dev.zbib.providerservice.model.mapper.ProviderMapper.*;

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

    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElse(null);
    }

    public ProviderResponse getProviderResponseById(Long id) {
        Provider provider = getProviderById(id);
        UserClientResponse userClientResponse = userClient.getUserClientResponseById(id);
        return toProviderResponse(provider, userClientResponse);
    }

    public void deleteProviderByUserId(Long userId) {
        Provider provider = getProviderById(userId);
        if (provider != null) {
            providerRepository.delete(provider);
        }
    }

    public Page<ProviderListResponse> getProviderPage(
            ServiceType serviceType,
            Boolean available,
            Double hourlyRate,
            String serviceArea,
            Pageable pageable) {
        var specification = ProviderSpecification.createFilter(serviceType, available, hourlyRate, serviceArea);
        Page<Provider> providerPage = providerRepository.findAll(specification, pageable);
        List<Long> userIds = providerPage.stream()
                .map(Provider::getId)
                .toList();
        List<UserListClientResponse> users = userClient.getUserListClientResponseByIdList(userIds);
        List<ProviderListResponse> providerList = toProviderListResponse(providerPage.getContent(), users);

        return new PageImpl<>(providerList, pageable, providerPage.getTotalElements());
    }

    public List<DetailsListResponse> getDetailListById(List<Long> ids) {
        List<Provider> providerList = providerRepository.findProvidersByIdIn(ids);
        return toDetailsListResponse(providerList);
    }
}
