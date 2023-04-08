package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.auth.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByAccount(String userAccount);

    UserRefreshToken findByAccountAndRefreshToken(String account, String refreshToken);

    void deleteByAccount(String userAccount);
}
