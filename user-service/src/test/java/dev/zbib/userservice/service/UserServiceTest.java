package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.exception.UserNotFoundException;
import dev.zbib.userservice.mapper.UserMapper;
import dev.zbib.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private CreateUserRequest request;
    private User user;

    @BeforeEach
    void setUp() {
        request = new CreateUserRequest();
        // Set request fields...

        user = new User();
        // Set user fields...
    }

    @Test
    void createUser_Success() {
        when(userMapper.toUser(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(new UserResponse(/* params */));

        UserResponse response = userService.createUser(request);
        assertEquals(/* expected */, response);
    }

    @Test
    void getUserById_NotFound() {
        long userId = 1L;
        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserResponseById(userId));
    }
} 