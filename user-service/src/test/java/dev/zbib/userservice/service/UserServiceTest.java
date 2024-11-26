package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.Address;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.enums.UserRoles;
import dev.zbib.userservice.model.request.CreateUserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private List<User> userList;
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        userList = Arrays.asList(new User(
                1L,
                "John",
                "Doe",
                "1234567890",
                "password",
                LocalDate.of(1990, 1, 1),
                null,
                UserRoles.USER,
                null,
                null), new User(
                2L,
                "Jane",
                "Doe",
                "0987654321",
                "password123",
                LocalDate.of(1985, 5, 15),
                null,
                UserRoles.PROVIDER,
                null,
                null), new User(
                3L,
                "Alice",
                "Smith",
                "1122334455",
                "password456",
                LocalDate.of(1992, 2, 20),
                null,
                UserRoles.USER,
                null,
                null), new User(
                4L,
                "Jason",
                "Hornez",
                "1121244455",
                "password456",
                LocalDate.of(1992, 2, 20),
                null,
                UserRoles.PROVIDER,
                null,
                null));
    }

    @Test
    void testCreateValidUser() {
        Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        address.setCountry("lebanon");
        address.setPostalCode("postalCode");

        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("John");
        request.setLastName("Mohammad");
        request.setPhoneNumber("76782106");
        request.setPassword("test");
        request.setRepeatPassword("test");
        request.setAddress(address);

        User savedUser = User.builder()
                .id(1L)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        Long userId = userService.createUser(request);
        assertThat(userId).isEqualTo(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserListResponseById() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        UserListResponse userListResponse1 = UserListResponse.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .profilePicture(null)
                .build();

        UserListResponse userListResponse2 = UserListResponse.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .profilePicture(null)
                .build();

        UserListResponse userListResponse3 = UserListResponse.builder()
                .id(3L)
                .firstName("Alice")
                .lastName("Smith")
                .profilePicture(null)
                .build();

        when(userRepository.findUsersByIdIn(eq(ids))).thenReturn(Arrays.asList(
                userList.get(0),
                userList.get(1),
                userList.get(2)));

        // When: Call the servic    e method without using UserMapper
        List<UserListResponse> result = userService.getUserListResponseById(ids);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(Arrays.asList(userListResponse1, userListResponse2, userListResponse3));
        verify(userRepository, times(1)).findUsersByIdIn(eq(ids));
    }


}