package com.bobfriend.bobfriends.controller.recruit;

import com.bobfriend.bobfriends.controller.recruit.dto.request.RecruitContentRequest;
import com.bobfriend.bobfriends.controller.recruit.dto.response.RecruitContentResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class RecruitContentController {

    @Operation(summary = "모집글 리스트 조회 (더미데이터)")
    @GetMapping("/recruit-contents")
    public Page<RecruitContentResponse> getContents(RecruitContentRequest request) {
        Random random = new Random();
       List<RecruitContentResponse> list = IntStream.range(0, 100)
               .mapToObj(it -> {
                   int totalCount = random.nextInt(9) + 2;
                   int count = random.nextInt(totalCount - 1) + 1;

                   RecruitContentResponse recruitContentResponse = new RecruitContentResponse();
                   recruitContentResponse.setId((long) (it + 1));
                   recruitContentResponse.setTotalPeopleCount(totalCount);
                   recruitContentResponse.setPeopleCount(count);
                   recruitContentResponse.setTitle(String.format("%s-%s", recruitContentResponse.getTitle(), it + 1));
                   return recruitContentResponse;
               }).collect(Collectors.toList());

       List<RecruitContentResponse> pageList = list.stream()
               .skip((long) request.getPageNumber() * request.getPageSize())
               .limit(request.getPageSize())
               .collect(Collectors.toList());

        return new PageImpl<>(pageList, request.getPageable(), list.size());
    }

}
