package com.bobfriend.bobfriends.config.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import static com.bobfriend.bobfriends.config.security.provider.JwtTokenProvider.Type.ACCESS;
import static com.bobfriend.bobfriends.config.security.provider.JwtTokenProvider.Type.REFRESH;

@Component
public class JwtTokenProvider {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    @Value("${jwt.secret-key.access}")
    private String accessKey;
    @Value("${jwt.secret-key.refresh}")
    private String refreshKey;
    private Map<Type, SecretKey> keyEnumMap;
    private Map<Type, Long> validTimeMap;

    @PostConstruct
    protected void init() {
        keyEnumMap = Map.of(
                ACCESS, Keys.hmacShaKeyFor(accessKey.getBytes(StandardCharsets.UTF_8)),
                REFRESH, Keys.hmacShaKeyFor(refreshKey.getBytes(StandardCharsets.UTF_8))
        );
        validTimeMap = Map.of(
                ACCESS, 30 * 60 * 1000L,
                REFRESH, 60 * 60 * 1000L
        );
    }

    public enum Type {
        ACCESS,
        REFRESH
    }

    public String createToken(String userId, Type type) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("name", "test");
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validTimeMap.get(type)))
                .signWith(keyEnumMap.get(type), SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(getUserId(token, ACCESS), token);
    }

    public String getUserId(String token, Type type) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(keyEnumMap.get(type))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public boolean validateToken(String token, Type type) {
        return Jwts.parserBuilder()
                .setSigningKey(keyEnumMap.get(type))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .after(new Date());
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        return resolveToken(bearerToken);
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
