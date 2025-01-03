package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.LoginRequest;
import dev.zbib.userservice.dto.RegisterRequest;
import dev.zbib.userservice.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;

    public TokenResponse register(RegisterRequest req) {
        userService.createUser(req);
        TokenResponse tokenResponse = tokenService.generateAccessToken(req.getEmail(), req.getPassword());
        log.info("User {} registered at {}.", req.getEmail(), LocalDateTime.now());
        return tokenResponse;
    }

    public TokenResponse login(LoginRequest req) {
        TokenResponse tokenResponse = tokenService.generateAccessToken(req.getEmail(), req.getPassword());
        log.info("User {} logged in at {}.", req.getEmail(), LocalDateTime.now());
        return tokenResponse;
    }
}
