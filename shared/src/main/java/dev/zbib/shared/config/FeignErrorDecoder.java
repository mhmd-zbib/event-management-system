package dev.zbib.shared.config;

import dev.zbib.shared.exception.ServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        
        if (responseStatus.is4xxClientError()) {
            return new ServiceException("Client error in service communication: " + response.reason(), responseStatus);
        } else if (responseStatus.is5xxServerError()) {
            return new ServiceException("Server error in service communication: " + response.reason(), responseStatus);
        }
        
        return new ServiceException("Unknown error in service communication", responseStatus);
    }
} 