package com.bobfriend.bobfriends.controller.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String account;
    private String nickname;
    private boolean localCertification;
}
