package com.bobfriend.bobfriends.controller.recruit.dto.request;

import com.bobfriend.bobfriends.constant.Constants;
import com.bobfriend.bobfriends.domain.file.File;
import com.bobfriend.bobfriends.domain.menu.Menu;
import com.bobfriend.bobfriends.domain.recruit.*;
import com.bobfriend.bobfriends.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RecruitCreateRequest {
    @JsonIgnore
    private String writer;
    @NotEmpty
    @Schema(description = "제목")
    private String title;
    @NotEmpty
    @Schema(description = "본문")
    private String content;
    @Schema(description = "배달비")
    private int deliveryPrice;
    @Min(2)
    @Schema(description = "모집인원")
    private int peopleCount;
    @NotNull
    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    @Schema(description = "모집종료시간", format = Constants.DATE_TIME_FORMAT, defaultValue = "2023-12-31 23:59:59")
    private LocalDateTime endDateTime;

    @Schema(description = "가게 설명")
    private String storeLocationDescription;
    @NotEmpty
    @Schema(description = "가게 주소")
    private String storeLocationAddress;
    @Schema(description = "가게 주소 위도")
    private double storeLocationLatitude;
    @Schema(description = "가게 주소 경도")
    private int storeLocationLongitude;

    @Schema(description = "픽업 설명")
    private String pickupLocationDescription;
    @NotEmpty
    @Schema(description = "픽업 주소")
    private String pickupLocationAddress;
    @Schema(description = "픽업 주소 위도")
    private double pickupLocationLatitude;
    @Schema(description = "픽업 주소 경도")
    private int pickupLocationLongitude;

    @Schema(description = "태그")
    private List<String> tags = new ArrayList<>();
    @Schema(description = "픽업 이미지 ids")
    private List<String> pickupImageIds = new ArrayList<>();
    @Schema(description = "메뉴")
    private List<MenuRequest> menus = new ArrayList<>();

    @JsonIgnore
    public RecruitContent getRecruitContent(String userAccount) {
        RecruitContent recruitContent = RecruitContent.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .deliveryPrice(deliveryPrice)
                .peopleCount(peopleCount)
                .endDateTime(endDateTime)
                .storeLocation(getStoreLocation())
                .pickupLocation(getPickupLocation())
                .build();
        recruitContent.addAllPickupImages(getPickupImages());
        recruitContent.addAllMenus(getMenus(userAccount));
        recruitContent.addJoinUsers(JoinUser.builder()
                .userAccount(userAccount).build());

        return recruitContent;
    }

    @JsonIgnore
    public List<Tag> getTags(Long recruitContentId) {
        return this.tags.stream()
                .map(it -> Tag.builder()
                        .name(it)
                        .recruitContentId(recruitContentId)
                        .build())
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<PickupImage> getPickupImages() {
        return this.pickupImageIds.stream()
                .map(it -> PickupImage.builder()
                        .file(new File(it))
                        .build())
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Menu> getMenus(String userAccount) {
        return this.menus.stream()
                .map(it -> Menu.builder()
                        .name(it.getName())
                        .count(it.getCount())
                        .price(it.getPrice())
                        .userAccount(userAccount)
                        .build())
                .collect(Collectors.toList());
    }

    private StoreLocation getStoreLocation() {
        return StoreLocation.builder()
                .description(storeLocationDescription)
                .address(storeLocationAddress)
                .latitude(storeLocationLatitude)
                .longitude(storeLocationLongitude)
                .build();
    }

    private PickupLocation getPickupLocation() {
        return PickupLocation.builder()
                .description(pickupLocationDescription)
                .address(pickupLocationAddress)
                .latitude(pickupLocationLatitude)
                .longitude(pickupLocationLongitude)
                .build();
    }
}
