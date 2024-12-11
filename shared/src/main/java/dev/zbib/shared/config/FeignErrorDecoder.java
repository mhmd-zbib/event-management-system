package dev.zbib.shared.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zbib.shared.constant.SystemExceptionMessage;
import dev.zbib.shared.dto.ErrorResponse;
import dev.zbib.shared.dto.AppException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
@Log4j2
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public FeignErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(
            String methodKey,
            Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        log.error("Feign client error on method: {}, status: {}", methodKey, responseStatus);

        try {
            String responseBody;
            try (InputStream bodyIs = response.body()
                    .asInputStream()) {
                responseBody = new String(bodyIs.readAllBytes(), StandardCharsets.UTF_8);
                log.error("Error response body: {}", responseBody);

                ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
                String errorMessage = errorResponse.getMessage() != null ?
                        errorResponse.getMessage() :
                        getDefaultMessage(responseStatus);

                return new AppException(errorMessage, responseStatus);
            }
        } catch (IOException e) {
            log.error("Failed to decode error response", e);
            return new AppException(
                    SystemExceptionMessage.SERVICE_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private String getDefaultMessage(HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> SystemExceptionMessage.VALIDATION_FAILED;
            case UNAUTHORIZED -> SystemExceptionMessage.UNAUTHORIZED;
            case FORBIDDEN -> SystemExceptionMessage.FORBIDDEN;
            case NOT_FOUND -> SystemExceptionMessage.NOT_FOUND;
            case INTERNAL_SERVER_ERROR -> SystemExceptionMessage.INTERNAL_SERVER_ERROR;
            case SERVICE_UNAVAILABLE -> SystemExceptionMessage.SERVICE_UNAVAILABLE;
            default -> SystemExceptionMessage.SERVICE_ERROR;
        };
    }
}


