package com.tkart.ecommerce.models.dto.common;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;
    private Object meta;
    private Map<String, String> errors;
    private String requestId;
    private long timestamp;

    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message(message)
                .data(data)
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }

    public static <T> ApiResponse<T> ok(String message, T data, Object meta) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message(message)
                .data(data)
                .meta(meta)
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(201)
                .message(message)
                .data(data)
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .data(null)
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }
}
