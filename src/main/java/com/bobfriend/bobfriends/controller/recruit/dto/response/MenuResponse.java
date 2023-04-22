package com.bobfriend.bobfriends.controller.recruit.dto.response;

import com.bobfriend.bobfriends.domain.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuResponse {
    private String name;
    private int price;
    private int count;

    public MenuResponse(Menu menu) {
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.count = menu.getCount();
    }
}
