package com.bobfriend.bobfriends.controller.recruit.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecruitContentResponse {
    private Long id;
    private String writer = "데이브";
    private String title = "또래오래 먹을사람~!!~!";
    private String locationDescription = "감자아파트 경비실 앞";
    private int peopleCount = 1;
    private int totalPeopleCount = 4;
    private List<String> tags = List.of("치킨", "또래오래");
}
