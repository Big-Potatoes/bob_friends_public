package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitContentRepository extends JpaRepository<RecruitContent, Long> {
}
