package com.project.partyparty.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends IllegalArgumentException {
    private final ExceptionMessage exceptionMessage;
}
