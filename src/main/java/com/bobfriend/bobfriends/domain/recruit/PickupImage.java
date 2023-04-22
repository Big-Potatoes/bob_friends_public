package com.bobfriend.bobfriends.domain.recruit;

import com.bobfriend.bobfriends.domain.BaseEntity;
import com.bobfriend.bobfriends.domain.file.File;
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
@Table(name = "pickup_image")
public class PickupImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruit_content_id")
    private RecruitContent recruitContent;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Transient
    private String fullUrl;

    public void setRecruitContent(RecruitContent recruitContent) {
        this.recruitContent = recruitContent;
    }

    public void initFullUrl(String appHost, String fileApiPath) {
       this.fullUrl = String.format("%s%s/%s", appHost, fileApiPath, file.getId());
    }
}
