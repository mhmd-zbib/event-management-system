package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.dto.response.*;
import dev.zbib.providerservice.mapper.ProviderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProviderService {
    private final ProviderMapper providerMapper;
    private final UserService userManagement;
    private final DetailsService detailsService;
    private final UserClient userClient;

    @Transactional
    public DetailsResponse createProvider(CreateProviderRequest request) {
        Long id = request.getId();
        userManagement.canBeProvider(id);
        userManagement.assignProviderRole(id);
        return detailsService.createDetails(request);
    }


    public ProviderResponse getProviderById(Long id) {
        DetailsResponse details = detailsService.getDetailsById(id);
        UserResponse user;
        user = userManagement.getUserById(id);
        return providerMapper.toProviderResponse(user, details);
    }


    public Page<ProviderListResponse> getProviderList(
            ProviderFilterRequest filter,
            Pageable page) {
        Page<DetailsListResponse> details = detailsService.getDetailList(filter, page);
        List<Long> providerIds = details.getContent()
                .stream()
                .map(DetailsListResponse::getId)
                .toList();

        List<UserListResponse> users = userClient.getUsersById(providerIds);
        List<ProviderListResponse> combinedList = combineDetailsWithUsers(details.getContent(), users);
        return new PageImpl<>(combinedList, details.getPageable(), details.getTotalElements());
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
                            user.lastName(),
                            user.profilePicture(),
                            provider.getServiceType(),
                            provider.getHourlyRate(),
                            provider.getServiceArea(),
                            provider.getRating(),
                            provider.isAvailable());
                })
                .collect(Collectors.toList());
    }
}
