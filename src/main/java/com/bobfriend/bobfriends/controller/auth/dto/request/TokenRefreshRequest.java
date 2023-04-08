package com.bobfriend.bobfriends.controller.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {
    private String accessToken;
    private String refreshToken;
}
