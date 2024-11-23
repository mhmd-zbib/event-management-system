package dev.zbib.userservice.model.mappers;

import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .birthDate(userRequest.getBirthDate())
                .profilePicture(userRequest.getProfilePicture())
                .address(userRequest.getAddress())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .profilePicture(user.getProfilePicture())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }


    public static List<UserListResponse> toUserListResponse(List<User> users) {
        return users.stream()
                .map(user -> {
                    UserListResponse response = new UserListResponse();
                    response.setId(user.getId());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setProfilePicture(user.getProfilePicture());
                    return response;
                })
                .collect(Collectors.toList());
    }

}
