package com.bobfriend.bobfriends.controller.recruit.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PickupLocationResponse {
    private String locationDescription;
    private String address;
    private double latitude;
    private double longitude;
    private List<String> images;

    public PickupLocationResponse(String locationDescription) {
        this.locationDescription = locationDescription;
        this.address = "서울특별시 중구 소공동 세종대로18길 2";
        this.latitude = 37.555946;
        this.longitude = 126.972317;
        this.images = List.of(
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAxMDlfMjA3%2FMDAxNjczMjI3Nzg0Nzkx.dZYYLUXgxRVcM0ra6EMRQ78hqIzLyOwB6VTrhppiw3kg.oSJe4tPqm8K-JMjP1ToMxSdvSR8ja-tIDPmj9Iv79awg.JPEG.carrie817%2F1673227781527.jpg",
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAzMTlfMjUw%2FMDAxNjQ3NjU1NzM1ODk5.-0C47jnBo-qEdkR4ieGxdSpzlcIPcsfPMCZnpBfTllcg.xd134cuhTjfNKdkvPUjWRBe7Sv885jhIBdXtbFdKstUg.JPEG.leeum777%2F20220319_104107.jpg"
        );
    }
}
