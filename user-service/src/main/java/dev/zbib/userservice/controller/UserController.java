package dev.zbib.userservice.controller;

import dev.zbib.shared.enums.UserRole;
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest req) {
        return ResponseEntity.ok(userService.createUser(req));
    }

    @GetMapping
    public ResponseEntity<List<UserListResponse>> getUsersByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(userService.getUsersByIds(ids));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<String> setRole(
            @PathVariable Long id,
            @RequestBody UserRole role) {
        userService.setRole(id, role);
        return ResponseEntity.ok("Role set");
    }

}
