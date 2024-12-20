package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.exception.UserNotFoundException;
import dev.zbib.userservice.mapper.UserMapper;
import dev.zbib.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(CreateUserRequest request) {
        User user = userMapper.toUser(request);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getUsersByIds(List<Long> ids) {
        return userRepository.findByIdIn(ids);
    }
}