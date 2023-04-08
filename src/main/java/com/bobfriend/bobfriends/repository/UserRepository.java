package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);

    User findByAccountAndPassword(String userAccount, String password);
}
