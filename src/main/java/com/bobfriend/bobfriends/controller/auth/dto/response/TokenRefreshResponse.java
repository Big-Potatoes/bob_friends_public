package com.bobfriend.bobfriends.controller.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshResponse {
    private String account;
    private String accessToken;

    public TokenRefreshResponse(String account, String accessToken) {
        this.account = account;
        this.accessToken = accessToken;
    }
}
