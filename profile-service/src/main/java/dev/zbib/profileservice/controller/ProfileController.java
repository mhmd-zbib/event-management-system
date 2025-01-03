package dev.zbib.profileservice.controller;

import dev.zbib.profileservice.dto.CreateProfileRequest;
import dev.zbib.profileservice.dto.ProfileListResponse;
import dev.zbib.profileservice.dto.ProfileResponse;
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
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody CreateProfileRequest request) {
        ProfileResponse response = profileService.createProfile(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable String id) {
        ProfileResponse response = profileService.getProfileById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfileListResponse>> getProfilesByIds(@RequestParam List<String> ids) {
        List<ProfileListResponse> responses = profileService.getProfileListByIds(ids);
        return ResponseEntity.ok(responses);
    }
}
