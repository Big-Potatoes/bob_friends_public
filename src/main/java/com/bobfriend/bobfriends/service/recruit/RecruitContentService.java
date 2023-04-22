package com.bobfriend.bobfriends.service.recruit;

import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitContentRequest;
import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitCreateRequest;
import com.bobfriend.bobfriends.domain.recruit.JoinUser;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitContentService {
    private final RecruitContentRepository recruitContentRepository;
    private final TagRepository tagRepository;
    private final PickupImageRepository pickupImageRepository;
    private final MenuRepository menuRepository;
    private final JoinUserRepository joinUserRepository;

    public void create(RecruitCreateRequest createRequest) {
        RecruitContent recruitContent = createRequest.getRecruitContent();
        recruitContentRepository.save(recruitContent);

        Long recruitContentId = recruitContent.getId();
        String userAccount = createRequest.getWriter();
        tagRepository.saveAll(createRequest.getTags(recruitContentId));
        pickupImageRepository.saveAll(createRequest.getPickupImages(recruitContentId));
        menuRepository.saveAll(createRequest.getMenus(recruitContentId, userAccount));
        joinUserRepository.save(JoinUser.builder()
                        .recruitContentId(recruitContentId)
                        .userAccount(userAccount).build());
    }

    public Page<RecruitContent> getContents(RecruitContentRequest request) {
        return recruitContentRepository.findAll(request.getPageable());
    }

    public List<JoinUser> findJoinUserByContentIds(Collection<Long> contentIds) {
        return joinUserRepository.findByRecruitContentIdIn(contentIds);
    }
}
