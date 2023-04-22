package com.bobfriend.bobfriends.controller.recruit;

import com.bobfriend.bobfriends.config.web.UserAccount;
import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitContentRequest;
import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitCreateRequest;
import com.bobfriend.bobfriends.controller.recruit.dto.response.RecruitContentDetailResponse;
import com.bobfriend.bobfriends.controller.recruit.dto.response.RecruitContentResponse;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.domain.tag.Tag;
import com.bobfriend.bobfriends.service.recruit.RecruitContentService;
import com.bobfriend.bobfriends.service.tag.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RecruitContentController {
    private final RecruitContentService recruitContentService;
    private final TagService tagService;

    @Operation(summary = "모집글 등록")
    @PostMapping("/recruit-contents")
    public void create(@Parameter(hidden = true) @UserAccount String account,
                       @RequestBody RecruitCreateRequest createRequest) {
        createRequest.setWriter(account);
        recruitContentService.create(createRequest);
    }

    @Operation(summary = "모집글 리스트 조회")
    @GetMapping("/recruit-contents")
    public Page<RecruitContentResponse> getContents(RecruitContentRequest request) {
       Page<RecruitContent> recruitContents = recruitContentService.getContents(request);
       List<Long> contentIds = recruitContents.getContent().stream()
               .map(RecruitContent::getId)
               .collect(Collectors.toList());
       Map<Long, List<Tag>> tagMap = tagService.findByContentIds(contentIds).stream()
               .collect(Collectors.groupingBy(Tag::getRecruitContentId));

        return recruitContents.map(it -> new RecruitContentResponse(it, tagMap.get(it.getId())));
    }

    @Operation(summary = "모집글 상세")
    @GetMapping("/recruit-content/{id}")
    public RecruitContentDetailResponse getContentById(@PathVariable Long id) {
        RecruitContent recruitContent = recruitContentService.getDetails(id);
        List<Tag> tags = tagService.findByContentId(recruitContent.getId());

        return new RecruitContentDetailResponse(recruitContent, tags);
    }
}
