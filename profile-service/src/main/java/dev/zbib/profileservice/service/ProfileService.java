package dev.zbib.profileservice.service;

import dev.zbib.profileservice.dto.request.CreateProfileDTO;
import dev.zbib.profileservice.dto.response.ProfileListResponse;
import dev.zbib.profileservice.dto.response.ProfileResponse;
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

    public ProfileResponse createProfile(CreateProfileDTO request) {
        Profile profile = buildProfile(request);
        profileRepository.save(profile);
        return buildProfileResponse(profile);
    }

    public ProfileResponse getProfileResponseById(Long id) {
        return profileRepository.findUserResponseById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<ProfileListResponse> getProfileListByIds(List<Long> ids) {
        return profileRepository.findByIdIn(ids);
    }

    public Profile getUserById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}