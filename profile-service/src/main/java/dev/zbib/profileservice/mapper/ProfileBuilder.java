package dev.zbib.profileservice.mapper;

import dev.zbib.profileservice.dto.CreateProfileRequest;
import dev.zbib.profileservice.dto.ProfileResponse;
import dev.zbib.profileservice.entity.Profile;

public class ProfileBuilder {

    public static Profile buildProfile(CreateProfileRequest dto) {
        return Profile.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .profilePicture(dto.getProfilePicture())
                .birthDate(dto.getBirthDate())
                .address(dto.getAddress())
                .build();
    }

    public static ProfileResponse buildProfileResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .profilePicture(profile.getProfilePicture())
                .birthDate(profile.getBirthDate())
                .address(profile.getAddress())
                .build();

    }
} 