package com.project.partyparty.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestHeaderException;

import java.security.SignatureException;

import static com.project.partyparty.common.exception.ExceptionMessage.*;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<Message> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getExceptionMessage());
        return Message.toExceptionResponseEntity(e.getExceptionMessage());
    }

    //정규식
    @ExceptionHandler({BindException.class})
    public ResponseEntity<Message> bindException(BindException ex) {
        return Message.toAllExceptionResponseEntity(HttpStatus.BAD_REQUEST, ex.getFieldError().getDefaultMessage(), ex.getBindingResult().getTarget());
    }

    //jwt 토큰 시간 없을시
    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<Message> missingRequestHeaderException(MissingRequestHeaderException ex) {
        return Message.toAllExceptionResponseEntity(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_MEMBER.getDetail(), null);
    }

    //마이리스트 토큰 없을시
    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<Message> signatureException(SignatureException ex) {
        return Message.toAllExceptionResponseEntity(HttpStatus.UNAUTHORIZED, INVALID_TOKEN.getDetail(), null);
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Message> handleAll(final Exception ex) {
        return Message.toAllExceptionResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString());
    }
}
