package dev.zbib.userservice.controller;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.shared.dto.UserResponse;
import dev.zbib.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequest req) {
        userService.createUser(req);
        return ResponseEntity.ok("User created");
    }

    @GetMapping
    public ResponseEntity<Page<UserListResponse>> getUserListResponse(Pageable pageable) {
        return ResponseEntity.ok(userService.getUserListResponse(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserResponseById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserResponseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }
}
