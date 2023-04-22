package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByRecruitContentIdIn(Collection<Long> recruitContentIds);

    List<Tag> findByRecruitContentId(Long recruitContentId);
}
