package com.project.webblog.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    USERNAME_DUPLICATION(HttpStatus.CONFLICT, "usename이 중복됐습니다"),
    NICKNAME_DUPLICATION(HttpStatus.CONFLICT,"닉네임이 중복됐습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
