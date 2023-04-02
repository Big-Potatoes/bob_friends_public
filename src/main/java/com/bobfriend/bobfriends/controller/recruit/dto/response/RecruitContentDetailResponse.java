package com.bobfriend.bobfriends.controller.recruit.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecruitContentDetailResponse {
    private Long id;
    private List<String> tags;
    private String title;
    private String writer;
    private int peopleCount;
    private int totalPeopleCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;
    private String content = "모집합니다요~~~ 또~~ 오래오래~~ ";
    // 지점정보
    private StoreLocationResponse storeLocation;
    // 픽업정보
    private PickupLocationResponse pickupLocation;
    // 배달비 정보
    private DeliveryPriceResponse deliveryPrice;
    // 메뉴리스트
    private List<String> menus = List.of(
            "메뉴1",
            "메뉴2",
            "메뉴3"
    );

    public RecruitContentDetailResponse(Long id, List<String> tags, String title, String writer,
                                        String locationDescription, int peopleCount, int totalPeopleCount,
                                        LocalDateTime createDateTime, LocalDateTime endDateTime) {

        this.id = id;
        this.tags = tags;
        this.title = title;
        this.writer = writer;
        this.peopleCount = peopleCount;
        this.totalPeopleCount = totalPeopleCount;
        this.createDateTime = createDateTime;
        this.endDateTime = endDateTime;
        this.storeLocation = new StoreLocationResponse();
        this.pickupLocation = new PickupLocationResponse(locationDescription);
        this.deliveryPrice = new DeliveryPriceResponse(3000, peopleCount, totalPeopleCount);
    }

    public static RecruitContentDetailResponse createDummy(RecruitContentResponse contentResponse) {
        return new RecruitContentDetailResponse(
                contentResponse.getId(),
                contentResponse.getTags(),
                contentResponse.getTitle(),
                contentResponse.getWriter(),
                contentResponse.getLocationDescription(),
                contentResponse.getPeopleCount(),
                contentResponse.getTotalPeopleCount(),
                contentResponse.getCreateDateTime(),
                contentResponse.getEndDateTime()
        );
    }
}
