package dev.zbib.userservice.model.mappers;

import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.model.entity.User;

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
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .profilePicture(user.getProfilePicture())
                .address(user.getAddress())
                .build();
    }
}
