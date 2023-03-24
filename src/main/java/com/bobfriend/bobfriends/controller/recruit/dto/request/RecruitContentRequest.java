package com.bobfriend.bobfriends.controller.recruit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class RecruitContentRequest {
    @Schema(description = "페이지 번호 (1 이하는 0페이지로 자동 변환)", defaultValue="1")
    private int pageNumber = 1;
    @Schema(description = "페이지 사이즈", defaultValue="5")
    private int pageSize = 5;

    public int getPageNumber() {
        return pageNumber < 1 ? 0 : pageNumber - 1;
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(getPageNumber(), pageSize);
    }
}
