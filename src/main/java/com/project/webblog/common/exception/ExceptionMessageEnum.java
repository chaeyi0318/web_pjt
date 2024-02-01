package com.project.webblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum ExceptionMessageEnum {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    USERNAME_DUPLICATION(HttpStatus.CONFLICT, "usename이 중복됐습니다"),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "계정 정보가 존재하지 않습니다"),
    INVALID_TOKEN(BAD_REQUEST, "유효하지 않는 JWT 서명입니다"),
    NICKNAME_DUPLICATION(HttpStatus.CONFLICT, "닉네임이 중복됐습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다");

    private final HttpStatus status;
    private final String message;
}
