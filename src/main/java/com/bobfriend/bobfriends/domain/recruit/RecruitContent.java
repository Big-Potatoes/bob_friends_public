package com.bobfriend.bobfriends.domain.recruit;

import com.bobfriend.bobfriends.domain.BaseEntity;
import com.bobfriend.bobfriends.domain.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    @BatchSize(size = 50)
    @OneToMany(mappedBy = "recruitContent", cascade = CascadeType.PERSIST)
    private List<PickupImage> pickupImages;
    @BatchSize(size = 50)
    @OneToMany(mappedBy = "recruitContent", cascade = CascadeType.PERSIST)
    private List<Menu> menus;
    @BatchSize(size = 50)
    @OneToMany(mappedBy = "recruitContent", cascade = CascadeType.PERSIST)
    private List<JoinUser> joinUsers;

    public String getLocationDescription() {
        if (Objects.isNull(pickupLocation)) {
            return null;
        }
        return pickupLocation.getDescription();
    }

    public void addAllPickupImages(List<PickupImage> pickupImages) {
        if (CollectionUtils.isEmpty(this.pickupImages)) {
            this.pickupImages = new ArrayList<>();
        }
        pickupImages.forEach(it -> it.setRecruitContent(this));
        this.pickupImages.addAll(pickupImages);
    }

    public void addAllMenus(List<Menu> menus) {
        if (CollectionUtils.isEmpty(this.menus)) {
            this.menus = new ArrayList<>();
        }
        menus.forEach(it -> it.setRecruitContent(this));
        this.menus.addAll(menus);
    }

    public void addJoinUsers(JoinUser joinUser) {
        if (CollectionUtils.isEmpty(this.joinUsers)) {
            this.joinUsers = new ArrayList<>();
        }
        joinUser.setRecruitContent(this);
        this.joinUsers.add(joinUser);
    }

    public List<PickupImage> getPickupImages() {
        if (CollectionUtils.isEmpty(pickupImages)) {
            return Collections.emptyList();
        }
        return pickupImages;
    }

    public List<Menu> getMenus() {
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        return menus;
    }

    public List<JoinUser> getJoinUsers() {
        if (CollectionUtils.isEmpty(joinUsers)) {
            return Collections.emptyList();
        }
        return joinUsers;
    }
}
