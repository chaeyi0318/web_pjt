package com.project.partyparty.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessMessage {
    private final HttpStatus httpStatus;
    private final String detail;

    public SuccessMessage(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }
}
