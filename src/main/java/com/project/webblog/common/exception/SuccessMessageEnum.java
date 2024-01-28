package com.project.webblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessageEnum {
    SELECT_SUCCESS(HttpStatus.OK, "조회 성공"),
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입 완료");

    private final HttpStatus status;
    private final String message;
}
