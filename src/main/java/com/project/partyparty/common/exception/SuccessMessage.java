package com.project.partyparty.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SuccessMessage {
    private final HttpStatus httpStatus;
    private final String detail;

}
