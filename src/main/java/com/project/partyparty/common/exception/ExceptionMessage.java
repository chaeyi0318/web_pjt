package com.project.partyparty.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionMessage {
    private final HttpStatus httpStatus;
    private final String detail;

    public ExceptionMessage(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }
}


