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
public class StoreLocation {
    @Column(name = "store_location_description")
    private String description;

    @Column(name = "store_location_address")
    private String address;

    @Column(name = "store_location_latitude")
    private Double latitude;

    @Column(name = "store_location_longitude")
    private Integer longitude;
}
