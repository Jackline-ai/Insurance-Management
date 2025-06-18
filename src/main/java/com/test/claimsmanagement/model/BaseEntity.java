package com.test.claimsmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(updatable = false)
    private LocalDate createdAt;

    @Column(insertable = false)
    private LocalDate updatedAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.startDate = LocalDate.now();
        this.endDate = this.startDate.plusYears(2);
        this.createdBy = "Jackline";
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
