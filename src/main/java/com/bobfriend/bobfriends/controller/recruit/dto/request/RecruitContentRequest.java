package com.bobfriend.bobfriends.controller.recruit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class RecruitContentRequest {
    private int pageNumber = 1;
    private int pageSize = 5;

    public int getPageNumber() {
        return pageNumber < 1 ? 0 : pageNumber - 1;
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(getPageNumber(), pageSize);
    }
}
