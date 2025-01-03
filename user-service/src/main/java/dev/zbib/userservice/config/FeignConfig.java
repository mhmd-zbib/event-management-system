package dev.zbib.userservice.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class FeignConfig {

    @Bean
    public FeignInterceptor feignAuthorizationInterceptor() {
        return new FeignInterceptor();
    }
}
