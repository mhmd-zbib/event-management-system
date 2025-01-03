package dev.zbib.profileservice.service;

import dev.zbib.profileservice.dto.CreateProfileRequest;
import dev.zbib.profileservice.dto.ProfileListResponse;
import dev.zbib.profileservice.dto.ProfileResponse;
import dev.zbib.profileservice.entity.Profile;
import dev.zbib.profileservice.exception.UserNotFoundException;
import dev.zbib.profileservice.mapper.ProfileBuilder;
import dev.zbib.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.profileservice.mapper.ProfileBuilder.buildProfile;
import static dev.zbib.profileservice.mapper.ProfileBuilder.buildProfileResponse;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileBuilder profileBuilder;

    public ProfileResponse createProfile(CreateProfileRequest request) {
        Profile profile = buildProfile(request);
        profileRepository.save(profile);
        return buildProfileResponse(profile);
    }

    public ProfileResponse getProfileById(String id) {
        return profileRepository.findProfileResponseById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<ProfileListResponse> getProfileListByIds(List<String> ids) {
        return profileRepository.findByIdIn(ids);
    }

}