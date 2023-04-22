package com.bobfriend.bobfriends.domain.recruit;

import com.bobfriend.bobfriends.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruit_content")
public class RecruitContent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "writer")
    private String writer;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "delivery_price")
    private Integer deliveryPrice;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @Embedded
    private StoreLocation storeLocation;

    @Embedded
    private PickupLocation pickupLocation;

    public String getLocationDescription() {
        if (Objects.isNull(pickupLocation)) {
            return null;
        }
        return pickupLocation.getDescription();
    }
}
