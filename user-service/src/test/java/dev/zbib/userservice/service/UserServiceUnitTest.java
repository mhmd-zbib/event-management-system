package dev.zbib.userservice.service;

import dev.zbib.userservice.client.ProviderClient;
import dev.zbib.userservice.model.entity.Address;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.enums.UserRoles;
import dev.zbib.userservice.model.request.CreateUserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProviderClient providerClient;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;
    private User user3;
    private CreateUserRequest createUserRequest;
    private Address address;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initializing the address object for user instances
        address = new Address("123 Street", "City", "State", "Country");

        // Initialize user1
        user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPhoneNumber("123456789");
        user1.setPassword("password123");
        user1.setBirthDate(LocalDate.of(1990, 1, 1));
        user1.setRole(UserRoles.USER);
        user1.setAddress(address);

        // Initialize user2
        user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Nut");
        user2.setPhoneNumber("414141");
        user2.setPassword("password123");
        user2.setBirthDate(LocalDate.of(1990, 1, 1));
        user2.setRole(UserRoles.USER);
        user2.setAddress(address);

        // Initialize user3 (with a provider role)
        user3 = new User();
        user3.setId(3L);
        user3.setFirstName("Mark");
        user3.setLastName("Smith");
        user3.setPhoneNumber("414141");
        user3.setPassword("password123");
        user3.setBirthDate(LocalDate.of(1990, 1, 1));
        user3.setRole(UserRoles.PROVIDER);
        user3.setAddress(address);

        // Initialize CreateUserRequest
        createUserRequest = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .password("password123")
                .repeatPassword("password123")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address(address)
                .build();

        // Initialize the user list
        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user1);
        Long userId = userService.createUser(createUserRequest);
        assertNotNull(userId);
        assertEquals(user1.getId(), userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUserResponseById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        UserResponse userResponse = userService.getUserResponseById(1L);
        assertNotNull(userResponse);
        assertEquals(user1.getId(), userResponse.getId());
    }

    @Test
    public void testDeleteUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        userService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);
        verify(providerClient, times(1)).deleteProvider(1L);
    }

    @Test
    public void testGetUserListResponseByIdList() {
        List<Long> ids = Arrays.asList(1L, 2L);
        when(userRepository.findUsersByIdIn(ids)).thenReturn(userList);
        List<UserListResponse> responses = userService.getUserListResponseByIdList(ids);
        assertEquals(2, responses.size());
    }
}