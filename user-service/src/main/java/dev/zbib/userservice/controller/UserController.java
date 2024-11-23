package dev.zbib.userservice.controller;

import dev.zbib.userservice.model.request.ProviderRequest;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserListResponse;
import dev.zbib.userservice.service.ProviderServiceClient;
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
    private final ProviderServiceClient providerServiceClient;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
    
    // TODO: Fix to
    @GetMapping()
    public List<UserListResponse> getUserListByIds(@RequestParam List<Long> ids) {
        return userService.getUserListById(ids);
    }

    @PostMapping("/provider")
    public ResponseEntity<String> createProvider(@RequestBody ProviderRequest providerRequest) {
        providerServiceClient.createProvider(providerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

}
