package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.dto.internal.ProviderValidationDTO;
import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.dto.response.*;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.providerservice.exception.ProviderNotEligibleException;
import dev.zbib.providerservice.exception.ProviderNotFoundException;
import dev.zbib.providerservice.mapper.ProviderMapper;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import dev.zbib.shared.enums.UserRole;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final UserClient userClient;
    private final ProviderMapper providerMapper;

    @Transactional
    public DetailsResponse createProvider(CreateProviderRequest request) {
        Long id = request.getId();
        validateProviderEligibility(id);
        assignProviderRole(id);

        Provider provider = providerMapper.toProvider(request);
        Provider createdProvider = providerRepository.save(provider);
        return providerMapper.toDetailsResponse(createdProvider);
    }


    public ProviderResponse getProvider(Long id) {
        DetailsResponse details = getDetailsById(id);
        UserResponse user;
        try {
            user = userClient.getUser(id);
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
        return providerMapper.toProviderResponse(user, details);
    }


    public Page<ProviderListResponse> getProviders(
            ProviderFilterRequest filter,
            Pageable page) {

        Specification<Provider> spec = ProviderSpecification.getProviders(filter);
        Page<DetailsListResponse> details = providerRepository.findAll(spec, page)
                .map(provider -> DetailsListResponse.builder()
                        .id(provider.getId())
                        .available(provider.isAvailable())
                        .serviceArea(provider.getServiceArea())
                        .serviceType(provider.getServiceType())
                        .hourlyRate(provider.getHourlyRate())
                        .rating(provider.getRating())
                        .build());

        List<Long> providerIds = details.getContent()
                .stream()
                .map(DetailsListResponse::getId)
                .toList();

        List<UserListResponse> users = userClient.getUsersById(providerIds);
        List<ProviderListResponse> combinedList = combineDetailsWithUsers(details.getContent(), users);
        return new PageImpl<>(combinedList, details.getPageable(), details.getTotalElements());
    }


    private DetailsResponse getDetailsById(Long id) {
        return providerRepository.findDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));
    }


    private void validateProviderEligibility(Long id) {
        try {
            EligibilityResponse eligibilityResponse = userClient.getProviderEligibility(id);
            log.info("reasons are  {} ", eligibilityResponse.getReasons());
            if (!eligibilityResponse.isEligible()) {
                throw new ProviderNotEligibleException(eligibilityResponse.getReasons());
            }
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
    }


    private void assignProviderRole(Long id) {
        try {
            userClient.setRole(id, UserRole.PROVIDER);
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
    }


    private List<ProviderListResponse> combineDetailsWithUsers(
            List<DetailsListResponse> details,
            List<UserListResponse> users) {
        Map<Long, UserListResponse> userMap = users.stream()
                .collect(Collectors.toMap(UserListResponse::id, user -> user));
        return details.stream()
                .map(provider -> {
                    UserListResponse user = userMap.get(provider.getId());
                    return new ProviderListResponse(
                            provider.getId(),
                            user.firstName(),
                            user.firstName(),
                            user.profilePicture(),
                            provider.getServiceType(),
                            provider.getHourlyRate(),
                            provider.getServiceArea(),
                            provider.getRating(),
                            provider.isAvailable());
                })
                .collect(Collectors.toList());
    }

    public EligibilityResponse validateProviderBooking(
            Long id,
            ServiceType serviceType) {

        List<String> reasons = new ArrayList<>();
        ProviderValidationDTO provider = providerRepository.findValidationDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));

        if (!serviceType.equals(provider.serviceType())) reasons.add("Provider service type mismatch");
        if (!provider.available()) reasons.add("Provider is not available");

        return EligibilityResponse.builder()
                .reasons(reasons)
                .eligible(!reasons.isEmpty())
                .build();
    }
}
