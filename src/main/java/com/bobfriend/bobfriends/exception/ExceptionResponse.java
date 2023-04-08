package com.bobfriend.bobfriends.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
