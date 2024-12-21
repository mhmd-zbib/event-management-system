package dev.zbib.userservice.controller;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserResponseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserListResponse>> getUserListByIds(@RequestParam List<Long> ids) {
        List<UserListResponse> responses = userService.getUserListByIds(ids);
        return ResponseEntity.ok(responses);
    }
}
