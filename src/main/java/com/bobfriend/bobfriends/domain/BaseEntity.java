package com.bobfriend.bobfriends.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "create_date_time", updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "update_date_time", updatable = true)
    private LocalDateTime updateDateTime;
}
