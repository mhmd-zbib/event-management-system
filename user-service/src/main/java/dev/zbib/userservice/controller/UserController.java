package dev.zbib.userservice.controller;

import dev.zbib.shared.dto.EligibilityResponse;
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
    public ResponseEntity<List<UserListResponse>> getUsersByIds(List<Long> ids) {
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


    @GetMapping("/{id}/validate/customer")
    public ResponseEntity<EligibilityResponse> getCustomerBookingEligibility(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(userService.getCustomerBookingEligibility(id));
    }

    @GetMapping("/{id}/validate/provider")
    public ResponseEntity<EligibilityResponse> getProviderBookingEligibility(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(userService.getProviderBookingEligibility(id));
    }
}
