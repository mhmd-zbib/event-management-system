package dev.zbib.userservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (template.url().startsWith("/profiles") && "POST".equals(template.method())) {
            return;
        }

        if (authentication.getCredentials() != null) {
            String token = (String) authentication.getCredentials();
            template.header("Authorization", "Bearer " + token);
        }
    }
}
