package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.mappers.UserMapper;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.userservice.model.mappers.UserMapper.toUserListResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserRequest request) {
        User user = UserMapper.toUser(request);
        userRepository.save(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElse(null);
        return UserMapper.toUserResponse(user);
    }

    public void deleteUser(long id) {
        UserResponse user = getUserById(id);
        userRepository.deleteById(user.getId());
    }

    public List<UserListResponse> getUserListById(Long[] id) {
        List<User> userList = userRepository.findUsersByProviderIds(id);
        List<UserListResponse> userListResponse = toUserListResponse(userList);
        return userListResponse;
    }
}
