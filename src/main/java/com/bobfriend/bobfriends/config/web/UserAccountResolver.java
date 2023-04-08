package com.bobfriend.bobfriends.config.web;

import com.bobfriend.bobfriends.config.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.bobfriend.bobfriends.config.security.provider.JwtTokenProvider.AUTHORIZATION_HEADER;

@Component
@RequiredArgsConstructor
public class UserAccountResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserAccount.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = jwtTokenProvider.resolveToken(webRequest.getHeader(AUTHORIZATION_HEADER));
        return jwtTokenProvider.getUserId(token, JwtTokenProvider.Type.ACCESS);
    }
}
