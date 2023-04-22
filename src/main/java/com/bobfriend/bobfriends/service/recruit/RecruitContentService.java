package com.bobfriend.bobfriends.service.recruit;

import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitContentRequest;
import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitCreateRequest;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.repository.RecruitContentRepository;
import com.bobfriend.bobfriends.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitContentService {
    @Value("${app.host}")
    private String appHost;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final RecruitContentRepository recruitContentRepository;
    private final TagRepository tagRepository;

    public void create(RecruitCreateRequest createRequest) {
        String userAccount = createRequest.getWriter();
        RecruitContent recruitContent = createRequest.getRecruitContent(userAccount);
        recruitContentRepository.save(recruitContent);

        Long recruitContentId = recruitContent.getId();
        tagRepository.saveAll(createRequest.getTags(recruitContentId));
    }

    @Transactional(readOnly = true)
    public Page<RecruitContent> getContents(RecruitContentRequest request) {
        return recruitContentRepository.findAll(request.getPageable());
    }

    @Transactional(readOnly = true)
    public RecruitContent getDetails(Long id) {
        RecruitContent recruitContent = recruitContentRepository.getReferenceById(id);
        String fileApiPath = String.format("%s/%s", contextPath, "file");
        recruitContent.getPickupImages().forEach(it -> it.initFullUrl(appHost, fileApiPath));
        return recruitContentRepository.getReferenceById(id);
    }
}
