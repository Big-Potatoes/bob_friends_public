package com.bobfriend.bobfriends.controller.recruit.dto.response;

import com.bobfriend.bobfriends.domain.recruit.StoreLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreLocationResponse {
    private String locationDescription;
    private String address;
    private double latitude;
    private double longitude;

    public StoreLocationResponse(StoreLocation storeLocation) {
        this.locationDescription = storeLocation.getDescription();
        this.address = storeLocation.getAddress();
        this.latitude = storeLocation.getLatitude();
        this.longitude = storeLocation.getLongitude();
    }
}
