package com.tkart.ecommerce.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;
    private long timestamp;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, 200, message, data, Instant.now().getEpochSecond());
    }

    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return new ApiResponse<>(true, code, message, data, Instant.now().getEpochSecond());
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(false, code, message, null, Instant.now().getEpochSecond());
    }

    public static <T> ApiResponse<T> error(int code, String message, T errors) {
        return new ApiResponse<>(false, code, message, errors, Instant.now().getEpochSecond());
    }
}
