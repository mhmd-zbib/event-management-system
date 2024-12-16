package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.dto.internal.ProviderValidationDTO;
import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.dto.response.*;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.providerservice.exception.ProviderEligibilityException;
import dev.zbib.providerservice.exception.ProviderNotFoundException;
import dev.zbib.providerservice.mapper.ProviderMapper;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.providerservice.repository.ProviderSpecification;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import dev.zbib.shared.enums.UserRole;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    public DetailsResponse createProvider(CreateProviderRequest request) {
        Long id = request.getId();
        validateProviderEligibility(id);
        assignProviderRole(id);

        Provider provider = providerMapper.toProvider(request);
        Provider createdProvider = providerRepository.save(provider);
        return providerMapper.toDetailsResponse(createdProvider);
    }


    public ProviderResponse getProvider(Long id) {
        DetailsResponse details = getDetails(id);
        UserResponse user;
        try {
            user = userClient.getUser(id);
        } catch (FeignException.NotFound e) {
            log.error("Provider with id {} not found", id);
            throw new ProviderNotFoundException(id);
        }
        return providerMapper.toProviderResponse(user, details);
    }


    public Page<ProviderListResponse> getDetails(ProviderFilterRequest filter) {
        var specification = ProviderSpecification.withRatingRange(filter.getMinRating(), filter.getMaxRating())
                .and(ProviderSpecification.withAvailability(filter.getAvailable()))
                .and(ProviderSpecification.withHourlyRateRange(filter.getMinHourlyRate(), filter.getMaxHourlyRate()))
                .and(ProviderSpecification.withServiceArea(filter.getServiceArea()));
        Pageable pageable = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                Sort.by(Sort.Direction.fromString(filter.getSortDirection()), filter.getSortBy()));
        Page<DetailsListResponse> details = providerRepository.findAllDetails(specification, pageable);

        List<Long> providerIds = details.getContent()
                .stream()
                .map(DetailsListResponse::id)
                .toList();

        List<UserListResponse> users = userClient.getUsersById(providerIds);
        List<ProviderListResponse> combinedList = combineDetailsWithUsers(details.getContent(), users);
        return new PageImpl<>(combinedList, details.getPageable(), details.getTotalElements());
    }


    private DetailsResponse getDetails(Long id) {
        return providerRepository.findDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));
    }


    private void validateProviderEligibility(Long id) {
        try {
            EligibilityResponse eligibilityResponse = userClient.getProviderEligibility(id);
            if (!eligibilityResponse.isEligible()) {
                throw new ProviderEligibilityException(eligibilityResponse.getReasons());
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
                    UserListResponse user = userMap.get(provider.id());
                    return new ProviderListResponse(
                            provider.id(),
                            user.firstName(),
                            user.firstName(),
                            user.profilePicture(),
                            provider.serviceType(),
                            provider.hourlyRate(),
                            provider.serviceArea(),
                            provider.rating(),
                            provider.available());
                })
                .collect(Collectors.toList());
    }

    public EligibilityResponse validateProviderBooking(
            Long id,
            ServiceType serviceType) {

        List<String> reasons = new ArrayList<>();
        ProviderValidationDTO provider = providerRepository.findValidationDetailsById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));

        if (!serviceType
                .equals(provider.serviceType())) reasons.add("Provider service type mismatch");
        if (!provider.availability()) reasons.add("Provider is not available");

        return EligibilityResponse.builder()
                .reasons(reasons)
                .eligible(!reasons.isEmpty())
                .build();
    }
}
