package com.bobfriend.bobfriends.controller.auth.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String account;
    private String password;
}
