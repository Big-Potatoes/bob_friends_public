package com.bobfriend.bobfriends.controller.auth;

import com.bobfriend.bobfriends.config.web.UserAccount;
import com.bobfriend.bobfriends.controller.auth.dto.request.SignInRequest;
import com.bobfriend.bobfriends.controller.auth.dto.request.SignUpRequest;
import com.bobfriend.bobfriends.controller.auth.dto.request.TokenRefreshRequest;
import com.bobfriend.bobfriends.controller.auth.dto.request.UserResponse;
import com.bobfriend.bobfriends.controller.auth.dto.response.SignInResponse;
import com.bobfriend.bobfriends.controller.auth.dto.response.TokenRefreshResponse;
import com.bobfriend.bobfriends.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "로그인")
    @PostMapping(value = "/auth/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @Operation(summary = "로그아웃")
    @DeleteMapping(value = "/auth/sign-out")
    public void signOut(@Parameter(hidden = true) @UserAccount String userAccount) {
        authService.signOut(userAccount);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/auth/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
    }

    @Operation(summary = "토큰 갱신")
    @PostMapping("/auth/token-refresh")
    public TokenRefreshResponse refreshToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return authService.refreshToken(tokenRefreshRequest);
    }

    @Operation(summary = "account 중복체크")
    @GetMapping("/auth/account/check-duplicate")
    public boolean checkAccountDuplicate(String account) {
        return authService.checkAccountDuplicate(account);
    }

    @Operation(summary = "유저정보 확인")
    @GetMapping("/auth/current")
    public UserResponse currentUserInfo(@Parameter(hidden = true) @UserAccount String account) {
        return authService.userInfo(account);
    }
}
