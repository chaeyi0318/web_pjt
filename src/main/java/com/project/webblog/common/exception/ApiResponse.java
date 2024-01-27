package com.project.webblog.common.exception;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private ApiHeader header;
    private ApiBody body;
    private int code;

    public ApiResponse(ApiHeader header, ApiBody body) {
        this.header = header;
        this.body = body;
    }

    public ApiResponse(HttpStatus status, ApiHeader header) {
        this.code = status.value();
        this.header = header;
    }

    public static<T> ApiResponse<T> success (HttpStatus status, T data) {
        return new ApiResponse<T>(new ApiHeader(status, "success"), new ApiBody(data, null));
    }

    public static<T> ApiResponse<T> fail(ErrorMessage errorCode) {
        return new ApiResponse<>(new ApiHeader(errorCode.getStatus(), errorCode.name()), new ApiBody(null, errorCode.getMessage()));
    }
}
