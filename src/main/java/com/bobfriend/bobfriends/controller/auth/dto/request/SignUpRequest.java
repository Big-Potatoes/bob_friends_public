package com.bobfriend.bobfriends.controller.auth.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class SignUpRequest {
    @NotEmpty
    private String account;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nickname;
}
