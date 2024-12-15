package dev.zbib.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private T data;
    private boolean success;
    
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("Success", data, true);
    }
    
    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(message, null, false);
    }
} 