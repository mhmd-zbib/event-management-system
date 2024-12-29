package dev.zbib.apigateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class AuthController {

    @GetMapping("/provider")
    @PreAuthorize("hasRole('provider')")
    public Mono<String> testProvider() {
        return Mono.just("provider");
    }

    @GetMapping
    public String test() {
        return "Hello";
    }

}
