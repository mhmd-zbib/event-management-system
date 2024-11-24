package dev.zbib.userservice.controller;

import dev.zbib.userservice.model.request.ProviderClientRequest;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequest userRequest) {
        Long id = userService.createUser(userRequest);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserResponseById(id);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping()
    public List<UserListResponse> getUserListByIds(@RequestParam List<Long> ids) {
        return userService.getUserListById(ids);
    }

    @PostMapping("/provider/{id}")
    public void registerProvider(
            @RequestBody ProviderClientRequest providerClientRequest,
            @PathVariable Long id) {
        providerClientRequest.setUserId(id);
        userService.registerProvider(providerClientRequest);
    }

    @PostMapping("/${userId}/favorites/${plumberId}")
    public void addFavoritePlumber(
            @PathVariable Long userId,
            @PathVariable Long plumberId) {
        userService.addFavoritePlumber(userId, plumberId);
    }

}
