package com.bobfriend.bobfriends.controller.recruit.dto.response;

import com.bobfriend.bobfriends.constant.Constants;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RecruitContentDetailResponse {
    private Long id;
    private List<String> tags;
    private String title;
    private String writer;
    private int peopleCount;
    private int totalPeopleCount;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createDateTime;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime endDateTime;
    private String content;
    // 지점정보
    private StoreLocationResponse storeLocation;
    // 픽업정보
    private PickupLocationResponse pickupLocation;
    // 배달비 정보
    private int deliveryPrice;
    // 메뉴리스트
    private List<MenuResponse> menus;

    public RecruitContentDetailResponse(RecruitContent recruitContent, List<Tag> tags) {
        this.id = recruitContent.getId();
        this.title = recruitContent.getTitle();
        this.writer = recruitContent.getWriter();
        this.content = recruitContent.getContent();
        this.deliveryPrice = recruitContent.getDeliveryPrice();
        this.peopleCount = recruitContent.getJoinUsers().size();
        this.totalPeopleCount = recruitContent.getPeopleCount();
        this.endDateTime = recruitContent.getEndDateTime();
        this.createDateTime = recruitContent.getCreateDateTime();
        this.storeLocation = new StoreLocationResponse(recruitContent.getStoreLocation());
        this.pickupLocation = new PickupLocationResponse(recruitContent.getPickupLocation(), recruitContent.getPickupImages());
        this.tags = CollectionUtils.isEmpty(tags) ? Collections.emptyList() : tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
        this.menus = recruitContent.getMenus().stream()
                .map(MenuResponse::new)
                .collect(Collectors.toList());
    }
}
