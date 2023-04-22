package com.bobfriend.bobfriends.service.recruit;

import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitCreateRequest;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.repository.MenuRepository;
import com.bobfriend.bobfriends.repository.PickupImageRepository;
import com.bobfriend.bobfriends.repository.RecruitContentRepository;
import com.bobfriend.bobfriends.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitContentService {
    private final RecruitContentRepository recruitContentRepository;
    private final TagRepository tagRepository;
    private final PickupImageRepository pickupImageRepository;
    private final MenuRepository menuRepository;

    public void create(RecruitCreateRequest createRequest) {
        RecruitContent recruitContent = createRequest.getRecruitContent();
        recruitContentRepository.save(recruitContent);

        Long recruitContentId = recruitContent.getId();
        String userAccount = createRequest.getWriter();
        tagRepository.saveAll(createRequest.getTags(recruitContentId));
        pickupImageRepository.saveAll(createRequest.getPickupImages(recruitContentId));
        menuRepository.saveAll(createRequest.getMenus(recruitContentId, userAccount));
    }
}
