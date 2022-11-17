package com.academy.springboot.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseAuditClass {
    @CreatedBy
    @Column(name="created_by")
    private String createdBy;

    @CreatedDate
    @Column(name= "created_at")
    private Date createdAt;

    @LastModifiedBy
    @Column(name="last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name="update_at")
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
