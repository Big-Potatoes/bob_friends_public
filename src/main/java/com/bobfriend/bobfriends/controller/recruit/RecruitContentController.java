package com.bobfriend.bobfriends.controller.recruit;

import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitContentRequest;
import com.bobfriend.bobfriends.controller.recruit.dto.response.RecruitContentDetailResponse;
import com.bobfriend.bobfriends.controller.recruit.dto.response.RecruitContentResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class RecruitContentController {
    private List<RecruitContentResponse> dummyList;

    @PostConstruct
    public void init() {
        Random random = new Random();
        dummyList = IntStream.range(0, 100)
                .mapToObj(it -> {
                    int totalCount = random.nextInt(9) + 2;
                    int count = random.nextInt(totalCount - 1) + 1;

                    RecruitContentResponse recruitContentResponse = new RecruitContentResponse();
                    recruitContentResponse.setId((long) (it + 1));
                    recruitContentResponse.setTotalPeopleCount(totalCount);
                    recruitContentResponse.setPeopleCount(count);
                    recruitContentResponse.setTitle(String.format("%s-%s", recruitContentResponse.getTitle(), it + 1));
                    recruitContentResponse.setCreateDateTime(LocalDateTime.now().plusMinutes(random.nextInt(60)));
                    recruitContentResponse.setEndDateTime(recruitContentResponse.getCreateDateTime().plusMinutes(random.nextInt(60)));
                    return recruitContentResponse;
                }).collect(Collectors.toList());
    }

    @Operation(summary = "모집글 리스트 조회 (더미데이터)")
    @GetMapping("/recruit-contents")
    public Page<RecruitContentResponse> getContents(RecruitContentRequest request) {
       List<RecruitContentResponse> pageList = dummyList.stream()
               .skip((long) request.getPageNumber() * request.getPageSize())
               .limit(request.getPageSize())
               .collect(Collectors.toList());

        return new PageImpl<>(pageList, request.getPageable(), dummyList.size());
    }

    @Operation(summary = "모집글 상세 (더미데이터)")
    @GetMapping("/recruit-content/{id}")
    public RecruitContentDetailResponse getContentById(@PathVariable Long id) {
        return dummyList.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .map(RecruitContentDetailResponse::createDummy)
                .orElse(null);
    }
}
