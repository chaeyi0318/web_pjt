package com.project.webblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessageEnum {
    SELECT_SUCCESS(HttpStatus.OK, "조회 성공"),
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입 완료"),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    CREATE_BOARD(HttpStatus.CREATED, "게시글 등록 성공");

    private final HttpStatus status;
    private final String message;
}
