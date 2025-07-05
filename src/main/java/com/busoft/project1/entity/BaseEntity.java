package com.busoft.project1.entity;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_in")
    private Date createdIn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_in")
    private Date updatedIn;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdIn = now;
        this.updatedIn = now;
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedIn = new Date();
    }

    public Date getUpdatedIn() {
        return updatedIn;
    }

    public void setUpdatedIn(Date updatedIn) {
        this.updatedIn = updatedIn;
    }

    public Date getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Date createdIn) {
        this.createdIn = createdIn;
    }
}
