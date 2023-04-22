package com.bobfriend.bobfriends.domain.menu;

import com.bobfriend.bobfriends.domain.BaseEntity;
import com.bobfriend.bobfriends.domain.recruit.RecruitContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "recruit_content_id")
    private RecruitContent recruitContent;

    public void setRecruitContent(RecruitContent recruitContent) {
        this.recruitContent = recruitContent;
    }
}
