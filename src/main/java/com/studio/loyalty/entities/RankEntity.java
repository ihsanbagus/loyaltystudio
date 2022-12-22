package com.studio.loyalty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ranks")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RankEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private EnumEntity.RANK rankname = EnumEntity.RANK.SILVER;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rank")
    @JsonIgnore
    @ToString.Exclude
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(mappedBy = "rank", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<BenefitEntity> benefits = new HashSet<>();

}
