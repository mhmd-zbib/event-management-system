package dev.zbib.shared.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String serviceName = extractServiceName(response.request().url());
        
        switch (response.status()) {
            case 503:
                return new ServiceException(
                    "SERVICE_UNAVAILABLE",
                    String.format(BaseException.SERVICE_UNAVAILABLE, serviceName),
                    HttpStatus.SERVICE_UNAVAILABLE,
                    serviceName
                );
            case 504:
                return new ServiceException(
                    "SERVICE_TIMEOUT",
                    String.format(BaseException.SERVICE_TIMEOUT, serviceName),
                    HttpStatus.GATEWAY_TIMEOUT,
                    serviceName
                );
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }

    private String extractServiceName(String url) {
        return url.split("/")[2].split("\\.")[0];
    }
} 