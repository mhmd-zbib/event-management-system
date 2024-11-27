package dev.zbib.userservice.model.mappers;

import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.request.CreateUserRequest;
import dev.zbib.userservice.model.response.ProviderDetailsListResponse;
import dev.zbib.userservice.model.response.ProviderListResponse;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .password(createUserRequest.getPassword())
                .birthDate(createUserRequest.getBirthDate())
                .profilePicture(createUserRequest.getProfilePicture())
                .address(createUserRequest.getAddress())
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
                .build();
    }


    public static UserListResponse toUserListResponse(User user) {
        return UserListResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .profilePicture(user.getProfilePicture())
                .build();
    }

    public static List<ProviderListResponse> toProviderListResponse(
            List<UserListResponse> userList,
            Map<Long, ProviderDetailsListResponse> providerDetailsMap) {
        return userList.stream()
                .map(user -> {
                    ProviderDetailsListResponse providerDetails = providerDetailsMap.get(user.getId());
                    if (providerDetails == null) {
                        return null;
                    }
                    return ProviderListResponse.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .profilePicture(user.getProfilePicture())
                            .serviceType(providerDetails.getServiceType())
                            .rating(providerDetails.getRating())
                            .available(providerDetails.isAvailable())
                            .hourlyRate(providerDetails.getHourlyRate())
                            .serviceArea(providerDetails.getServiceArea())
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


}
