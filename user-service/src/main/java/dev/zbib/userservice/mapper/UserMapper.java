package dev.zbib.userservice.mapper;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "isVerified", ignore = true)
    @Mapping(target = "isBlocked", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toUser(CreateUserRequest createUserRequest);

} 