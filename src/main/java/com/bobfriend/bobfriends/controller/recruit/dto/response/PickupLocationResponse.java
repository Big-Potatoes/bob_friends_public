package com.bobfriend.bobfriends.controller.recruit.dto.response;

import com.bobfriend.bobfriends.domain.recruit.PickupImage;
import com.bobfriend.bobfriends.domain.recruit.PickupLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PickupLocationResponse {
    private String locationDescription;
    private String address;
    private double latitude;
    private double longitude;
    private List<String> images;

    public PickupLocationResponse(PickupLocation pickupLocation, List<PickupImage> pickupImages) {
        this.locationDescription = pickupLocation.getDescription();
        this.address = pickupLocation.getAddress();
        this.latitude = pickupLocation.getLatitude();
        this.longitude = pickupLocation.getLongitude();
        this.images = pickupImages.stream()
                .map(PickupImage::getFullUrl)
                .collect(Collectors.toList());
    }
}
