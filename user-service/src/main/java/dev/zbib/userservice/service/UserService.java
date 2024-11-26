package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.Favorite;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.enums.UserRoles;
import dev.zbib.userservice.model.mappers.UserMapper;
import dev.zbib.userservice.model.request.CreateUserRequest;
import dev.zbib.userservice.model.request.RegisterProviderRequest;
import dev.zbib.userservice.model.response.ProviderDetailsListResponse;
import dev.zbib.userservice.model.response.ProviderListResponse;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.repository.FavoriteRepository;
import dev.zbib.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static dev.zbib.userservice.model.mappers.UserMapper.toUser;
import static dev.zbib.userservice.model.mappers.UserMapper.toUserResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProviderClient providerClient;
    private final FavoriteRepository favoriteRepository;

    public Long createUser(CreateUserRequest request) {
        User user = toUser(request);
        user.setRole(UserRoles.USER);
        return userRepository.save(user)
                .getId();
    }

    public UserResponse getUserResponseById(Long id) {
        User user = getUserById(id);
        return toUserResponse(user);
    }

    private User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElse(null);
        return user;
    }

    public void deleteUserById(long id) {
        User user = getUserById(id);
        userRepository.deleteById(user.getId());
        if (user != null && UserRoles.PROVIDER.equals(user.getRole())) {
            providerClient.deleteProvider(id);
        }
    }

    public List<UserListResponse> getUserListResponseById(List<Long> id) {
        List<User> userList = userRepository.findUsersByIdIn(id);
        return userList.stream()
                .map(UserMapper::toUserListResponse)
                .collect(Collectors.toList());
    }

    public void registerProvider(
            Long id,
            RegisterProviderRequest registerProviderRequest) {
        User user = userRepository.findById(id)
                .orElse(null);
        if (user.getRole()
                .equals(UserRoles.PROVIDER)) {
            throw new IllegalStateException("User is already registered as a provider.");
        }
        user.setRole(UserRoles.PROVIDER);
        userRepository.save(user);
        providerClient.registerProvider(id, registerProviderRequest);
    }

    public void addProviderToFavorites(
            Long userId,
            Long providerId) {
        User user = getUserById(userId);
        Favorite favorite = Favorite.builder()
                .user(user)
                .providerId(providerId)
                .build();
        favoriteRepository.save(favorite);
    }

    public Page<ProviderListResponse> getFavoriteProviderList(
            Long id,
            Pageable pageable) {

        Page<Long> favoriteProviderIdPage = favoriteRepository.findProviderIdsByUserId(id, pageable);
        List<UserListResponse> userResponseList = getUserListResponseById(favoriteProviderIdPage.getContent());
        List<ProviderDetailsListResponse> providerDetailsListResponse = providerClient.getProviderDetailsListById(
                favoriteProviderIdPage.getContent());

        Map<Long, ProviderDetailsListResponse> providerDetailsListResponseMap = providerDetailsListResponse.stream()
                .collect(Collectors.toMap(ProviderDetailsListResponse::getId, provider -> provider));

        List<ProviderListResponse> providerList = userResponseList.stream()
                .filter(user -> favoriteProviderIdPage.getContent()
                        .contains(user.getId()))
                .map(user -> {
                    ProviderDetailsListResponse providerDetails = providerDetailsListResponseMap.get(user.getId());
                    if (providerDetails == null) {
                        return null;
                    }
                    return ProviderListResponse.builder()
                            .id(user.getId())
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
        return new PageImpl<>(providerList, pageable, favoriteProviderIdPage.getTotalElements());

    }
    //  Test
}
