package dev.zbib.userservice.controller;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody CreateUserRequest req) {
        return ResponseEntity.ok()
                .body(authService.createUser(req));
    }

    @GetMapping()
    public ResponseEntity<List<UserRepresentation>> getUsers() {
        return ResponseEntity.ok()
                .body(authService.getUsers());
    }
}
