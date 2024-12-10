package dev.zbib.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeignErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private String service;
}
