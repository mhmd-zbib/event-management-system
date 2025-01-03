package dev.zbib.profileservice.controller;

import dev.zbib.profileservice.dto.request.CreateProfileDTO;
import dev.zbib.profileservice.dto.response.ProfileListResponse;
import dev.zbib.profileservice.dto.response.ProfileResponse;
import dev.zbib.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody CreateProfileDTO request) {
        ProfileResponse response = profileService.createProfile(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable Long id) {
        ProfileResponse response = profileService.getProfileResponseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfileListResponse>> getProfilesByIds(@RequestParam List<Long> ids) {
        List<ProfileListResponse> responses = profileService.getProfileListByIds(ids);
        return ResponseEntity.ok(responses);
    }
}
