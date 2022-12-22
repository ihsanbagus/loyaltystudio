package com.studio.loyalty.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "benefits")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BenefitEntity extends BaseEntity {

    @NotEmpty
    private String discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_benefits")
    @ToString.Exclude
    private RankEntity rank;

}
