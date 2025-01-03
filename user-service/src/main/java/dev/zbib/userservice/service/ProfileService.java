package dev.zbib.userservice.service;

import dev.zbib.userservice.client.ProfileClient;
import dev.zbib.userservice.dto.CreateProfileDTO;
import dev.zbib.userservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileClient profileClient;

    public void createProfile(RegisterRequest req) {
        CreateProfileDTO dto = CreateProfileDTO.builder()
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
