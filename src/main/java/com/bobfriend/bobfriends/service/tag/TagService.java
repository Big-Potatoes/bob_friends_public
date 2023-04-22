package com.bobfriend.bobfriends.service.tag;

import com.bobfriend.bobfriends.domain.tag.Tag;
import com.bobfriend.bobfriends.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> findByContentIds(Collection<Long> recruitContentIds) {
        return tagRepository.findByRecruitContentIdIn(recruitContentIds);
    }

    public List<Tag> findByContentId(Long recruitContentId) {
        return tagRepository.findByRecruitContentId(recruitContentId);
    }
}
