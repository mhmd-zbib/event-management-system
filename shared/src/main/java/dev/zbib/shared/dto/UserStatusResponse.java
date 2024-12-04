package dev.zbib.shared.dto;

import lombok.Getter;

@Getter
public class UserStatusResponse {
    private Long id;
    private String role;
    private String status;
    private boolean isVerified;
    private boolean isBlocked;
}
