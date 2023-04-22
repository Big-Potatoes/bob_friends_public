package com.bobfriend.bobfriends.controller.recruit.dto.request;

import com.bobfriend.bobfriends.domain.menu.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {
    private String name;
    private int price;
    private int count;

    public Menu getMenu(Long recruitContentId, String userAccount) {
        return Menu.builder()
                .recruitContentId(recruitContentId)
                .userAccount(userAccount)
                .count(count)
                .price(price)
                .build();
    }
}
