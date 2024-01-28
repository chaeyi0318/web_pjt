package com.project.webblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;

    public static ResponseEntity<ApiResponse> toExceptionResponseEntity(ExceptionMessageEnum exceptionMessage) {
        return ResponseEntity
                .status(exceptionMessage.getStatus())
                .body(ApiResponse.builder()
                        .status(!exceptionMessage.getStatus().isError())
                        .message(exceptionMessage.getMessage())
                        .data(exceptionMessage)
                        .build()
                );
    }

    public static ResponseEntity<ApiResponse> toAllExceptionResponseEntity(HttpStatus httpStatus, String message, Object data) {
        return ResponseEntity
                .status(httpStatus)
                .body(ApiResponse.builder()
                        .status(false)
                        .message(message)
                        .data(data)
                        .build()
                );
    }

    public static ResponseEntity<ApiResponse> toResponseEntity(SuccessMessageEnum successMessage) {
        return ResponseEntity
                .status(successMessage.getStatus())
                .body(ApiResponse.builder()
                        .status(!successMessage.getStatus().isError())
                        .message(successMessage.getMessage())
                        .data(successMessage)
                        .build()
                );
    }

    //리턴 값 있을때 사용
    public static ResponseEntity<ApiResponse> toResponseEntity(SuccessMessageEnum successMessage, Object data) {
        return ResponseEntity
                .status(successMessage.getStatus())
                .body(ApiResponse.builder()
                        .status(!successMessage.getStatus().isError())
                        .message(successMessage.getMessage())
                        .data(data)
                        .build()
                );
    }
}
