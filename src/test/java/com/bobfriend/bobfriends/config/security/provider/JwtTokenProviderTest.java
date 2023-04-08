package com.bobfriend.bobfriends.config.security.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(value = JwtTokenProvider.class)
class JwtTokenProviderTest {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Test
    void name() {
        System.out.println(jwtTokenProvider.createToken("dave.h", JwtTokenProvider.Type.ACCESS));
    }
}