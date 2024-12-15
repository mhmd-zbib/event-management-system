package dev.zbib.shared.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
    
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("Accept", "application/json");
            template.header("Content-Type", "application/json");
        };
    }
} 