package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.recruit.JoinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface JoinUserRepository extends JpaRepository<JoinUser, Long> {
    List<JoinUser> findByRecruitContentIdIn(Collection<Long> contentIds);
}
