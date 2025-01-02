package dev.zbib.authservice.service;

import dev.zbib.authservice.dto.LoginRequest;
import dev.zbib.authservice.dto.RegisterRequest;
import dev.zbib.authservice.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;

    public TokenResponse register(RegisterRequest req) {
        userService.createUser(req);
        return tokenService.generateToken(req.getEmail(), req.getPassword());
    }

    public TokenResponse login(LoginRequest req) {
        return tokenService.generateToken(req.getEmail(), req.getPassword());
    }
}
