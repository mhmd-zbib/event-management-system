package dev.zbib.userservice.controller;

import dev.zbib.userservice.model.request.CreateUserRequest;
import dev.zbib.userservice.model.request.RegisterProviderRequest;
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
    public ResponseEntity<Long> createUser(@RequestBody CreateUserRequest createUserRequest) {
        Long id = userService.createUser(createUserRequest);
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


    @PostMapping("/{userId}/favorites/{providerId}")
    public void addProviderToFavorites(
            @PathVariable Long userId,
            @PathVariable Long providerId) {
        userService.addProviderToFavorites(userId, providerId);
    }

    @PostMapping("/{id}/provider")
    public void registerProvider(
            @RequestBody RegisterProviderRequest registerProviderRequest,
            @PathVariable Long id) {
        userService.registerProvider(id, registerProviderRequest);
    }

    //  INTERNAL
    @GetMapping()
    public List<UserListResponse> getUserListByIds(@RequestParam List<Long> ids) {
        return userService.getUserListById(ids);
    }
}
