package com.bobfriend.bobfriends.controller.recruit.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryPriceResponse {
    private int totalPrice;
    private int peopleCount;
    private int payPrice;

    public DeliveryPriceResponse(int totalPrice, int peopleCount, int totalPeopleCount) {
        this.totalPrice = totalPrice;
        this.peopleCount = peopleCount == totalPeopleCount ? peopleCount : peopleCount + 1;
        this.payPrice = (int) Math.floor((double) totalPrice / peopleCount);
    }
}
