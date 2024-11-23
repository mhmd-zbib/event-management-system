package dev.zbib.userservice.controller;

import dev.zbib.userservice.model.request.ProviderClientRequest;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
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
            @RequestParam ProviderClientRequest providerClientRequest,
            @PathVariable Long id) {
        providerClientRequest.setUserId(id);
        userService.registerProvider(providerClientRequest);
    }

}
