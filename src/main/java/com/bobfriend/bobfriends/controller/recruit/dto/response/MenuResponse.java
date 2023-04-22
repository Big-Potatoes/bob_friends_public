package com.bobfriend.bobfriends.controller.recruit.dto.response;

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
}
