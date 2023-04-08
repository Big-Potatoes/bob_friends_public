package com.bobfriend.bobfriends.controller.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingInResponse {
    private String accessToken;
    private String refreshToken;

    public SingInResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
