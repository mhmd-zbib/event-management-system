package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.LoginRequest;
import dev.zbib.userservice.dto.RegisterRequest;
import dev.zbib.userservice.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;

    public TokenResponse register(RegisterRequest req) {
        userService.createUser(req);
        return tokenService.generateAccessToken(req.getEmail(), req.getPassword());
    }

    public TokenResponse login(LoginRequest req) {
        return tokenService.generateAccessToken(req.getEmail(), req.getPassword());
    }
}
