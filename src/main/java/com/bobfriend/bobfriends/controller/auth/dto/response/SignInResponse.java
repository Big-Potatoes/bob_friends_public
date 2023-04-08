package com.bobfriend.bobfriends.controller.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {
    private String accessToken;
    private String refreshToken;

    public SignInResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
