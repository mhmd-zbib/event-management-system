package dev.zbib.profileservice.service;

import dev.zbib.profileservice.dto.request.CreateProfileDTO;
import dev.zbib.profileservice.dto.response.ProfileListResponse;
import dev.zbib.profileservice.dto.response.UserResponse;
import dev.zbib.profileservice.entity.Profile;
import dev.zbib.profileservice.exception.UserNotFoundException;
import dev.zbib.profileservice.mapper.ProfileBuilder;
import dev.zbib.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileBuilder profileBuilder;
    private final UserValidationService validation;

    public UserResponse createProfile(CreateProfileDTO request) {
        validation.validateUserCreation(request);
        Profile profile = profileBuilder.buildProfile(request);
        profileRepository.save(profile);
        return profileBuilder.toUserResponse(profile);
    }

    public UserResponse getProfileResponseById(Long id) {
        return profileRepository.findUserResponseById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<ProfileListResponse> getProfileListByIds(List<Long> ids) {
        return profileRepository.findByIdIn(ids);
    }

    public Profile getUserById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}