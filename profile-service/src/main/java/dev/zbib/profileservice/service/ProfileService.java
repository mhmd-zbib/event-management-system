package dev.zbib.profileservice.service;

import dev.zbib.profileservice.dto.CreateProfileRequest;
import dev.zbib.profileservice.dto.ProfileListResponse;
import dev.zbib.profileservice.dto.ProfileResponse;
import dev.zbib.profileservice.entity.Profile;
import dev.zbib.profileservice.exception.UserNotFoundException;
import dev.zbib.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.profileservice.mapper.ProfileBuilder.buildProfile;
import static dev.zbib.profileservice.mapper.ProfileBuilder.buildProfileResponse;
import static dev.zbib.shared.utils.JwtUtil.getUserId;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponse createProfile(CreateProfileRequest request) {
        Profile profile = buildProfile(request);
        profileRepository.save(profile);
        ProfileResponse res = buildProfileResponse(profile);
        log.info("Created profile for user {} ", request.getId());
        return res;
    }

    public ProfileResponse getProfile() {
        String id = getUserId();
        return profileRepository.findProfileResponseById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public ProfileResponse getProfileById(String id) {
        return profileRepository.findProfileResponseById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<ProfileListResponse> getProfileListByIds(List<String> ids) {
        return profileRepository.findByIdIn(ids);
    }

}