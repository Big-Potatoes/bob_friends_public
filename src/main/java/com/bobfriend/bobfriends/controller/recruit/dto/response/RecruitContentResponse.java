package com.bobfriend.bobfriends.controller.recruit.dto.response;

import com.bobfriend.bobfriends.constant.Constants;
import com.bobfriend.bobfriends.domain.recruit.JoinUser;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitContentResponse {
    private Long id;
    private String writer = "데이브";
    private String title = "또래오래 먹을사람~!!~!";
    private String locationDescription = "서울역 앞";
    private int peopleCount = 1;
    private int totalPeopleCount = 4;
    private List<String> tags = List.of("치킨", "또래오래");
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createDateTime;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime endDateTime;

    public RecruitContentResponse(RecruitContent recruitContent, List<Tag> tags, List<JoinUser> joinUsers) {
        this.id = recruitContent.getId();
        this.writer = recruitContent.getWriter();
        this.title = recruitContent.getTitle();
        this.locationDescription = recruitContent.getLocationDescription();
        this.peopleCount = CollectionUtils.isEmpty(joinUsers) ? 0 : joinUsers.size();
        this.totalPeopleCount = recruitContent.getPeopleCount();
        this.tags = CollectionUtils.isEmpty(tags) ? Collections.emptyList() : tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
        this.createDateTime = recruitContent.getCreateDateTime();
        this.endDateTime = recruitContent.getEndDateTime();
    }
}
