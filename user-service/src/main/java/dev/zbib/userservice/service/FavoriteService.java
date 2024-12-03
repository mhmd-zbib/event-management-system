package dev.zbib.userservice.service;

import dev.zbib.userservice.client.ProviderClient;
import dev.zbib.userservice.entity.Favorite;
import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.integration.ProviderDetailsListResponse;
import dev.zbib.userservice.dto.response.ProviderListResponse;
import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.zbib.userservice.mapper.UserMapper.toProviderListResponse;

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

        List<ProviderListResponse> providerList = toProviderListResponse(userList, providerDetailsMap);
        return new PageImpl<>(providerList, pageable, favoriteProviderIdPage.getTotalElements());
    }
}
