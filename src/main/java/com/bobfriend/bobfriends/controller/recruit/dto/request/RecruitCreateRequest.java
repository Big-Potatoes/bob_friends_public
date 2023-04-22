package com.bobfriend.bobfriends.controller.recruit.dto.request;

import com.bobfriend.bobfriends.constant.Constants;
import com.bobfriend.bobfriends.domain.menu.Menu;
import com.bobfriend.bobfriends.domain.recruit.PickupImage;
import com.bobfriend.bobfriends.domain.recruit.PickupLocation;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import com.bobfriend.bobfriends.domain.recruit.StoreLocation;
import com.bobfriend.bobfriends.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String title;
    @NotEmpty
    private String content;
    private int deliveryPrice;
    private int peopleCount;
    @NotNull
    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime endDateTime;

    @NotEmpty
    private String storeLocationAddress;
    private double storeLocationLatitude;
    private int storeLocationLongitude;

    private String pickupLocationDescription;
    @NotEmpty
    private String pickupLocationAddress;
    private double pickupLocationLatitude;
    private int pickupLocationLongitude;

    private List<String> tags = new ArrayList<>();
    private List<String> pickupImageIds = new ArrayList<>();
    private List<MenuRequest> menus = new ArrayList<>();

    @JsonIgnore
    public RecruitContent getRecruitContent() {
        return RecruitContent.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .deliveryPrice(deliveryPrice)
                .peopleCount(peopleCount)
                .endDateTime(endDateTime)
                .storeLocation(getStoreLocation())
                .pickupLocation(getPickupLocation())
                .build();
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
    public List<PickupImage> getPickupImages(Long recruitContentId) {
        return this.pickupImageIds.stream()
                .map(it -> PickupImage.builder()
                        .fileId(it)
                        .recruitContentId(recruitContentId)
                        .build())
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Menu> getMenus(Long recruitContentId, String userAccount) {
        return this.menus.stream()
                .map(it -> it.getMenu(recruitContentId, userAccount))
                .collect(Collectors.toList());
    }

    private StoreLocation getStoreLocation() {
        return StoreLocation.builder()
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
