package dev.zbib.profileservice.mapper;

import dev.zbib.profileservice.dto.request.CreateProfileDTO;
import dev.zbib.profileservice.dto.response.UserResponse;
import dev.zbib.profileservice.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileBuilder {
    UserResponse toUserResponse(Profile profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "isVerified", ignore = true)
    @Mapping(target = "isBlocked", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Profile buildProfile(CreateProfileDTO createProfileDTO);

} 