package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.Favorite;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.response.ProviderDetailsListResponse;
import dev.zbib.userservice.model.response.ProviderListResponse;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ProviderClient providerClient;


    public void addProviderToFavorites(
            Long userId,
            Long providerId) {
        User user = userService.getUserById(userId);
        Favorite favorite = Favorite.builder()
                .user(user)
                .providerId(providerId)
                .build();
        favoriteRepository.save(favorite);
    }

    public Page<ProviderListResponse> getFavoriteProviderPage(
            Long userId,
            Pageable pageable) {

        Page<Long> favoriteProviderIdPage = favoriteRepository.findProviderIdsByUserId(userId, pageable);
        List<Long> favoriteProviderIdList = favoriteProviderIdPage.getContent();

        List<UserListResponse> userList = userService.getUserListResponseByIdList(favoriteProviderIdList);
        List<ProviderDetailsListResponse> providerDetailsList = providerClient.getProviderDetailsListById(
                favoriteProviderIdList);

        Map<Long, ProviderDetailsListResponse> providerDetailsMap = providerDetailsList.stream()
                .collect(Collectors.toMap(ProviderDetailsListResponse::getId, provider -> provider));

        List<ProviderListResponse> providerList = mapToProviderListResponse(userList, providerDetailsMap);
        return new PageImpl<>(providerList, pageable, favoriteProviderIdPage.getTotalElements());
    }

    private List<ProviderListResponse> mapToProviderListResponse(
            List<UserListResponse> userList,
            Map<Long, ProviderDetailsListResponse> providerDetailsMap) {
        return userList.stream()
                .map(user -> {
                    ProviderDetailsListResponse providerDetails = providerDetailsMap.get(user.getId());
                    if (providerDetails == null) {
                        return null;
                    }
                    return ProviderListResponse.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .profilePicture(user.getProfilePicture())
                            .serviceType(providerDetails.getServiceType())
                            .rating(providerDetails.getRating())
                            .available(providerDetails.isAvailable())
                            .hourlyRate(providerDetails.getHourlyRate())
                            .serviceArea(providerDetails.getServiceArea())
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
