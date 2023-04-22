package com.bobfriend.bobfriends.domain.recruit;

import com.bobfriend.bobfriends.domain.BaseEntity;
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
@Table(name = "join_user")
public class JoinUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "recruit_content_id")
    private Long recruitContentId;

    @Column(name = "user_account")
    private String userAccount;
}
