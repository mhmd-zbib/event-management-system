package dev.zbib.userservice.service;

import dev.zbib.shared.dto.UserResponse;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import dev.zbib.shared.exception.UserException;
import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {

    private final UserRepository userRepository;

    public void createUser(@Valid CreateUserRequest req) {
        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .address(req.getAddress())
                .password(req.getPassword())
                .profilePicture(req.getProfilePicture())
                .status(AccountStatus.PENDING_VERIFICATION)
                .phoneNumber(req.getPhoneNumber())
                .birthDate(req.getBirthDate())
                .isVerified(false)
                .role(UserRoles.USER)
                .build();
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserException::notFound);
    }

    public Page<UserListResponse> getUserListResponse(Pageable page) {
        return userRepository.findUserListResponse(page);
    }

    public UserResponse getUserResponseById(Long id) {
        return userRepository.findUserResponseById(id);
    }

    public void deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public void setUserRole(Long id) {
        User user = getUserById(id);
        user.setRole(UserRoles.USER);
        userRepository.save(user);
    }

}