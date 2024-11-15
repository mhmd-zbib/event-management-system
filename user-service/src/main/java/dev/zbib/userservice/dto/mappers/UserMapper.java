package dev.zbib.userservice.dto;

import dev.zbib.userservice.entity.User;

public class UserMapper {
    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .birthDate(userRequest.getBirthDate())
                .profilePicture(userRequest.getProfilePicture())
                .history(userRequest.getHistory())
                .address(userRequest.getAddress())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .profilePicture(user.getProfilePicture())
                .address(user.getAddress())
                .build();
    }
}
