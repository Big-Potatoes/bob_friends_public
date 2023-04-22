package com.bobfriend.bobfriends.domain.recruit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PickupLocation {
    @Column(name = "pickup_location_description")
    private String description;

    @Column(name = "pickup_location_address")
    private String address;

    @Column(name = "pickup_location_latitude")
    private Double latitude;

    @Column(name = "pickup_location_longitude")
    private Integer longitude;
}
