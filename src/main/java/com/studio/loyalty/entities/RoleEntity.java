package com.studio.loyalty.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private EnumEntity.ROLES rolename = EnumEntity.ROLES.CUSTOMER;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    @JsonIgnore
    @ToString.Exclude
    private Set<UserEntity> users = new HashSet<>();
}
