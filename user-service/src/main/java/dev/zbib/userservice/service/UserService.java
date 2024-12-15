package dev.zbib.userservice.service;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;
import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.ProviderEligibilityResponse;
import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.exception.UserNotFoundException;
import dev.zbib.userservice.mapper.UserMapper;
import dev.zbib.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(@Valid CreateUserRequest req) {
        User user = userMapper.toUser(req);
        User createdUser = userRepository.save(user);
        return userMapper.toUserResponse(createdUser);
    }

    public UserResponse getUserById(@Valid Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUserById(Long id) {
        User user = getEntityUserById(id);
        userRepository.delete(user);
    }

    public List<UserListResponse> getUsersByIds(
            List<Long> ids) {
        return userRepository.findUsersById(ids);
    }

    public ProviderEligibilityResponse getProviderEligibility(Long id) {
        User user = getEntityUserById(id);
        List<String> reasons = new ArrayList<>();
        if (!user.isVerified()) reasons.add("Your account is not verified");
        if (user.getRole() == UserRole.PROVIDER) reasons.add("You are already a provider");
        if (user.getAccountStatus() != AccountStatus.ACTIVE) reasons.add("Your account is not active");

        return ProviderEligibilityResponse.builder()
                .eligible(reasons.isEmpty())
                .reasons(reasons)
                .build();
    }

    private User getEntityUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}