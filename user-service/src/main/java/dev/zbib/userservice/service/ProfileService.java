package dev.zbib.userservice.service;

import dev.zbib.userservice.client.ProfileClient;
import dev.zbib.userservice.dto.CreateProfileRequest;
import dev.zbib.userservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileClient profileClient;

    public void createProfile(
            String userId,
            RegisterRequest req) {
        CreateProfileRequest dto = CreateProfileRequest.builder()
                .userId(userId)
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .phoneNumber(req.getPhoneNumber())
                .address(req.getAddress())
                .profilePicture(req.getProfilePicture())
                .birthDate(req.getBirthDate())
                .build();
        profileClient.createProfile(dto);
    }
}
