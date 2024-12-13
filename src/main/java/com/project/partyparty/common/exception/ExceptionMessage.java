package com.project.partyparty.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    USER_FORBIDDEN(HttpStatus.FORBIDDEN,"권한이 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "Invalid JWT signature, 유효하지 않는 JWT 서명 입니다"),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT,"중복된 username이 존재합니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT,"중복된 이메일이 존재합니다.");
    
    private final HttpStatus httpStatus;
    private final String detail;
}


