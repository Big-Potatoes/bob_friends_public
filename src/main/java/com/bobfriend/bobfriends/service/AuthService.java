package com.bobfriend.bobfriends.service;

import com.bobfriend.bobfriends.config.security.provider.JwtTokenProvider;
import com.bobfriend.bobfriends.controller.auth.dto.request.SignInRequest;
import com.bobfriend.bobfriends.controller.auth.dto.request.SignUpRequest;
import com.bobfriend.bobfriends.controller.auth.dto.request.TokenRefreshRequest;
import com.bobfriend.bobfriends.controller.auth.dto.request.UserResponse;
import com.bobfriend.bobfriends.controller.auth.dto.response.SignInResponse;
import com.bobfriend.bobfriends.controller.auth.dto.response.TokenRefreshResponse;
import com.bobfriend.bobfriends.domain.auth.User;
import com.bobfriend.bobfriends.domain.auth.UserRefreshToken;
import com.bobfriend.bobfriends.repository.UserRefreshTokenRepository;
import com.bobfriend.bobfriends.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpRequest signUpRequest) {
        checkAccountDuplicate(signUpRequest.getAccount());

        userRepository.save(User.builder()
                .account(signUpRequest.getAccount())
                .nickname(signUpRequest.getNickname())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build());
    }

    public boolean checkAccountDuplicate(String account) {
        User user = userRepository.findByAccount(account);
        if (Objects.nonNull(user)) {
            throw new IllegalArgumentException("중복된 아이디가 있습니다.");
        }

        return true;
    }

    @SneakyThrows
    public SignInResponse signIn(SignInRequest signInRequest) {
        final String userAccount = signInRequest.getAccount();
        User user = userRepository.findByAccount(userAccount);
        if (Objects.isNull(user) || !passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("계정 정보가 맞지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createToken(userAccount, JwtTokenProvider.Type.ACCESS);
        String refreshToken = jwtTokenProvider.createToken(userAccount, JwtTokenProvider.Type.REFRESH);
        updateRefreshToken(userAccount, refreshToken);
        return new SignInResponse(accessToken, refreshToken);
    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        jwtTokenProvider.validateToken(tokenRefreshRequest.getRefreshToken(), JwtTokenProvider.Type.REFRESH);

        String userAccount = jwtTokenProvider.getUserId(tokenRefreshRequest.getAccessToken(), JwtTokenProvider.Type.ACCESS);
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByAccountAndRefreshToken(userAccount, tokenRefreshRequest.getRefreshToken());
        if (Objects.isNull(userRefreshToken)) {
            throw new IllegalArgumentException("access token, refresh token 유저 정보가 다릅니다.");
        }

        String accessToken = jwtTokenProvider.createToken(userAccount, JwtTokenProvider.Type.ACCESS);
        return new TokenRefreshResponse(userAccount, accessToken);
    }

    private void updateRefreshToken(String userAccount, String refreshToken) {
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByAccount(userAccount);
        if (Objects.isNull(userRefreshToken)) {
            userRefreshTokenRepository.save(new UserRefreshToken(userAccount, refreshToken));
            return;
        }
        userRefreshToken.updateRefreshToken(refreshToken);
    }

    public UserResponse userInfo(String account) {
        User user = userRepository.findByAccount(account);
        return UserResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .nickname(user.getNickname())
                .build();
    }

    public void signOut(String userAccount) {
        userRefreshTokenRepository.deleteByAccount(userAccount);
    }
}
