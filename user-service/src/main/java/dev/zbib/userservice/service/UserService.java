package dev.zbib.userservice.service;

import dev.zbib.userservice.client.ProviderClient;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.enums.UserRoles;
import dev.zbib.userservice.model.mappers.UserMapper;
import dev.zbib.userservice.model.request.CreateUserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static dev.zbib.userservice.model.mappers.UserMapper.toUser;
import static dev.zbib.userservice.model.mappers.UserMapper.toUserResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProviderClient providerClient;

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

    public User getUserById(Long id) {
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

    public List<UserListResponse> getUserListResponseByIdList(List<Long> id) {
        List<User> userList = userRepository.findUsersByIdIn(id);
        return userList.stream()
                .map(UserMapper::toUserListResponse)
                .collect(Collectors.toList());
    }

    public void changeRole(
            Long id,
            UserRoles role) {
        User user = getUserById(id);
        user.setRole(role);
        userRepository.save(user);
    }
}