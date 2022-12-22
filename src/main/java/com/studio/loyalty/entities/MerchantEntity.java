package com.studio.loyalty.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "merchants")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MerchantEntity extends BaseEntity {

    @NotEmpty
    private String merchantname;

    @NotEmpty
    @Column(unique = true)
    private String phone;

    @NotEmpty
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "merchant")
    @ToString.Exclude
    private Set<VoucherEntity> vouchers = new HashSet<>();

}
