package com.bobfriend.bobfriends.controller.recruit.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreLocationResponse {
    private String address;
    private double latitude;
    private double longitude;

    public StoreLocationResponse() {
        this.address = "서울 용산구 후암로28길 10";
        this.latitude = 37.549795;
        this.longitude = 126.9778016;
    }
}
