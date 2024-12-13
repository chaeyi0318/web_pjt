package com.project.partyparty.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.,"권한이 없습니다.");

    private final HttpStatus status;
    private final String message;
}


