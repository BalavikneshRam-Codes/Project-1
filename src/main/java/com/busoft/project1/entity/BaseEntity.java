package com.busoft.project1.entity;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_in", nullable = true)
    private Date createdIn;

    @PrePersist
    protected void onCreate() {
        this.createdIn = new Date();
    }

    public Date getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Date createdIn) {
        this.createdIn = createdIn;
    }
}
