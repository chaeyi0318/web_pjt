package com.project.webblog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

import static com.project.webblog.common.exception.ExceptionMessageEnum.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getExceptionMessage());
        return ApiResponse.toExceptionResponseEntity(e.getExceptionMessage());
    }

    //정규식
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ApiResponse> bindException(BindException ex) {
        return ApiResponse.toAllExceptionResponseEntity(HttpStatus.BAD_REQUEST, ex.getFieldError().getDefaultMessage(), ex.getBindingResult().getTarget());
    }

    //jwt 토큰 시간 없을시
    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<ApiResponse> missingRequestHeaderException(MissingRequestHeaderException ex) {
        return ApiResponse.toAllExceptionResponseEntity(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_MEMBER.getMessage(), null);
    }

    //마이리스트 토큰 없을시
    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<ApiResponse> signatureException(SignatureException ex) {
        return ApiResponse.toAllExceptionResponseEntity(HttpStatus.UNAUTHORIZED, INVALID_TOKEN.getMessage(), null);
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse> handleAll(final Exception ex) {
        return ApiResponse.toAllExceptionResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString());
    }
}