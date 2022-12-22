package com.studio.loyalty.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.security.auth.UserPrincipal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private String id;

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @JsonIgnore
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", insertable = false)
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    protected void onCreated() {
        id = UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.active = true;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            Object pricipal = authentication.getPrincipal();
            if(pricipal instanceof UserPrincipal){
                this.createdBy = ((UserPrincipal) pricipal).getName();
            }
        } else {
            this.createdBy = "SYSTEM";
        }
    }

    @PreUpdate
    protected void onUpdated() {
        this.updatedDate = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            Object pricipal =authentication.getPrincipal();
            if(pricipal instanceof UserPrincipal){
                this.createdBy = ((UserPrincipal) pricipal).getName();
            }
        } else {
            this.updatedBy = "SYSTEM";
        }
    }
}
