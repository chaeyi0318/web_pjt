package com.project.webblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiHeader {
    private HttpStatus resultCode;
    private String message;
}
