package com.project.webblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ApiBody <T> {
    private T data;
    private T msg;
}
