package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.Favorite;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.enums.UserRoles;
import dev.zbib.userservice.model.request.ProviderClientRequest;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.repository.FavoriteRepository;
import dev.zbib.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.userservice.model.mappers.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProviderClient providerClient;
    private final FavoriteRepository favoriteRepository;

    public Long createUser(UserRequest request) {
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
        UserResponse user = getUserResponseById(id);
        userRepository.deleteById(user.getId());
        if (user != null && UserRoles.PROVIDER.equals(user.getRole())) {
            providerClient.deleteProvider(id);
        }
    }

    public List<UserListResponse> getUserListById(List<Long> id) {
        List<User> userList = userRepository.findUsersByIdIn(id);
        return toUserListResponse(userList);
    }

    public void registerProvider(ProviderClientRequest providerClientRequest) {
        User user = userRepository.findById(providerClientRequest.getUserId())
                .orElse(null);
        if (user.getRole()
                .equals(UserRoles.PROVIDER)) {
            throw new IllegalStateException("User is already registered as a provider.");
        }
        user.setRole(UserRoles.PROVIDER);
        userRepository.save(user);
        providerClient.createProvider(providerClientRequest);
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
}
