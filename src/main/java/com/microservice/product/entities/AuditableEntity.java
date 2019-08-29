package com.microservice.product.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;

@Data
@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
public class AuditableEntity {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Calendar createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_by")
    private Calendar lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private String createBy;

//    @LastModifiedBy
//    private String lastModifiedBy;
}
