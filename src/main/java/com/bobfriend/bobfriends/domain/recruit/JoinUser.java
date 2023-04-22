package com.bobfriend.bobfriends.domain.recruit;

import com.bobfriend.bobfriends.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "join_user")
public class JoinUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_account")
    private String userAccount;

    @ManyToOne
    @JoinColumn(name = "recruit_content_id")
    private RecruitContent recruitContent;

    public void setRecruitContent(RecruitContent recruitContent) {
        this.recruitContent = recruitContent;
    }
}
