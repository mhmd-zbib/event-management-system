package dev.zbib.providerservice.service;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.ProviderRequest;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.UserClientListResponse;
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
import static dev.zbib.providerservice.model.mapper.ProviderMapper.toResponseList;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserClient userServiceClient;

    public void createProvider(ProviderRequest request) {
        Provider provider = toProvider(request);
        providerRepository.save(provider);
    }

    public Provider getProviderByUserId(Long userId) {
        return providerRepository.findByUserId(userId)
                .orElse(null);
    }

    public void deleteProviderByUserId(Long userId) {
        log.warn("id  {}", userId);
        Provider provider = getProviderByUserId(userId);
        providerRepository.delete(provider);
    }

    public Page<ProviderListResponse> getProviders(
            ServiceType serviceType,
            Boolean available,
            Double hourlyRate,
            String serviceArea,
            Pageable pageable) {

        Specification<Provider> specification = ProviderSpecification.createFilter(serviceType,
                available,
                hourlyRate,
                serviceArea);

        Page<Provider> providerPage = providerRepository.findAll(specification, pageable);

        List<Long> userIds = providerPage.getContent()
                .stream()
                .map(Provider::getUserId)
                .collect(Collectors.toList());

        List<UserClientListResponse> users = userServiceClient.getUsersByIds(userIds);

        Map<Long, UserClientListResponse> userMap = users.stream()
                .collect(Collectors.toMap(UserClientListResponse::getId, user -> user));

        List<ProviderListResponse> responses = providerPage.getContent()
                .stream()
                .map(provider -> toResponseList(provider, userMap.get(provider.getUserId())))
                .collect(Collectors.toList());

        return new PageImpl<>(responses, pageable, providerPage.getTotalElements());
    }

}
