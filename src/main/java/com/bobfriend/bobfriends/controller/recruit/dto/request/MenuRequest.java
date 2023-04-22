package com.bobfriend.bobfriends.controller.recruit.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {
    @Schema(description = "메뉴 명")
    private String name;
    @Schema(description = "가격")
    private int price;
    @Schema(description = "수량")
    private int count;
}
