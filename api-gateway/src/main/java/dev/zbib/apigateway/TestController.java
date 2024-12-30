package dev.zbib.apigateway;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Mono<String> test() {
        return Mono.just("WORKING!!!");
    }

    @PreAuthorize("hasRole('ROLE_PROVIDER')")
    @GetMapping("/provider")
    public Mono<String> testProvider() {
        return Mono.just("WORKING!!!");
    }

    @PreAuthorize("hasRole('customer')")
    @GetMapping("/customer")
    public Mono<String> testCustomer() {
        return Mono.just("WORKING!!!");
    }
}
