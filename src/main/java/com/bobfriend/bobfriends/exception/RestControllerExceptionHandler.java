package com.bobfriend.bobfriends.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse ExceptionHandler(Exception e) {
        log.error("error: {}", e.getMessage(), e);
        return new ExceptionResponse("서버 오류");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("error: {}", e.getMessage(), e);
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void AuthenticationExceptionHandler(AuthenticationException e) {
        log.error("error: {}", e.getMessage(), e);
    }

}
