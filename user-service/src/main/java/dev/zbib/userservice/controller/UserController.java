package dev.zbib.userservice.controller;

import dev.zbib.userservice.enums.UserRoles;
import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.dto.response.ProviderListResponse;
import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.service.FavoriteService;
import dev.zbib.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FavoriteService favoriteService;

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
        favoriteService.addProviderToFavorites(userId, providerId);
    }

    @PutMapping("/{id}")
    public void changeRole(
            @PathVariable Long id,
            @RequestBody
            UserRoles role) {
        userService.setRole(id, role);
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<Page<ProviderListResponse>> getFavoriteProviderPage(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(favoriteService.getFavoriteProviderPage(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookingPage(@PathVariable Long id) {
        /**
         * TODO: return the following:
         * [
         *   { "booking_id": 201, "provider_name": "John Doe", "status": "completed", "date": "2024-12-01" },
         *   { "booking_id": 202, "provider_name": "Jane Smith", "status": "pending", "date": "2024-12-05" }
         * ]
         */
        return ResponseEntity.ok("Soon...");
    }

    //  INTERNAL
    @GetMapping()
    public List<UserListResponse> getUserListByIds(@RequestParam List<Long> ids) {
        return userService.getUserListResponseByIdList(ids);
    }
}
